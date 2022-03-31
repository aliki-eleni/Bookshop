package com.unipi.p18240.bookshop.ui.home;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.unipi.p18240.bookshop.R;
import com.unipi.p18240.bookshop.adapters.BestSellerAdapter;
import com.unipi.p18240.bookshop.adapters.PopularAdapters;
import com.unipi.p18240.bookshop.adapters.RecommendedAdapter;
import com.unipi.p18240.bookshop.adapters.ViewAllAdapter;
import com.unipi.p18240.bookshop.models.BestSellerModel;
import com.unipi.p18240.bookshop.models.PopularModel;
import com.unipi.p18240.bookshop.models.RecommendedModel;
import com.unipi.p18240.bookshop.models.ViewAllModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

public class HomeFragment extends Fragment {

    // Initialise variables
    FirebaseFirestore db;

    RecyclerView popularBook, bestSeller, recommendedBooks;

    //popular items
    List<PopularModel> popularModelList;
    PopularAdapters popularAdapters;

    // Best seller items
    List<BestSellerModel> bestSellerModelList;
    BestSellerAdapter bestSellerAdapter;

    // Recommended items
    List<RecommendedModel> recommendedModelList;
    RecommendedAdapter recommendedAdapter;

    // Search viewer
    private List<ViewAllModel> viewAllModelList;
    private ViewAllAdapter viewAllAdapter;

    // Initialise variables
    EditText searchBar;
    ImageButton voice;

    SpeechRecognizer speechRecognizer;

    @SuppressLint({"NotifyDataSetChanged", "ClickableViewAccessibility"})
    // Method that creates new view holders for the list
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        // Get fire base instance
        db= FirebaseFirestore.getInstance();

        // Define the view field based on id from the xml file
        popularBook = root.findViewById(R.id.pop_books);
        bestSeller = root.findViewById(R.id.bss_books);
        recommendedBooks = root.findViewById(R.id.rec_books);
        voice = root.findViewById(R.id.button);
        searchBar = root.findViewById(R.id.search_bar);

        // Check if user gave permissions
        if (ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {

            permissionResult.launch(Manifest.permission.RECORD_AUDIO);
        }

        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(getContext());
        Intent speechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

        // Create on touch listener for when the user hold the button
voice.setOnTouchListener((v, motionEvent) -> {

            switch (motionEvent.getAction()){

                // When user is holding the button
                case MotionEvent.ACTION_DOWN:
                speechRecognizer.startListening(speechRecognizerIntent);
                    searchBar.setText("");
                    break;

                // When user isn't holding the button
                case MotionEvent.ACTION_UP:
                    speechRecognizer.stopListening();
                    searchBar.setHint("Search a book here.....");
                    break;
            }
            return false;
        });

        speechRecognizer.setRecognitionListener(new RecognitionListener() {

            @Override
            public void onReadyForSpeech(Bundle params) {

            }

            @Override
            public void onBeginningOfSpeech() {

            }

            @Override
            public void onRmsChanged(float rmsdB) {

            }

            @Override
            public void onBufferReceived(byte[] buffer) {

            }

            @Override
            public void onEndOfSpeech() {

            }

            @Override
            public void onError(int error) {

            }

            @Override
            public void onResults(Bundle results){
                // Set the voice message to text on the search bar
                ArrayList<String> data = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                    searchBar.setText(data.get(0));
            }
            @Override
            public void onPartialResults(Bundle partialResults) {

            }

            @Override
            public void onEvent(int eventType, Bundle params) {

            }
        });

        // Create recycler viewer to display all the documents from a specific collection
        popularBook.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));

        popularModelList = new ArrayList<>();
        popularAdapters = new PopularAdapters(getActivity(), popularModelList);
        popularBook.setAdapter(popularAdapters);

        // Connect to data base in order to get all the documents from the collection
        db.collection("Popular_books")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()){
                        for (QueryDocumentSnapshot doc : Objects.requireNonNull(task.getResult())){
                            PopularModel pm = doc.toObject(PopularModel.class);
                            popularModelList.add(pm);
                            popularAdapters.notifyDataSetChanged();
                        }
                    }
                    else{
                        Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                    }
                });

        // Create recycler viewer to display all the documents from a specific collection
        bestSeller.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));

        bestSellerModelList = new ArrayList<>();
        bestSellerAdapter = new BestSellerAdapter(getActivity(), bestSellerModelList);
        bestSeller.setAdapter(bestSellerAdapter);

        // Connect to data base in order to get all the documents from the collection
        db.collection("Best_sellers")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()){
                        for (QueryDocumentSnapshot doc : Objects.requireNonNull(task.getResult())){
                            BestSellerModel bsm = doc.toObject(BestSellerModel.class);
                            bestSellerModelList.add(bsm);
                            bestSellerAdapter.notifyDataSetChanged();

                        }
                    }
                    else{
                        Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                    }
                });

        // Create recycler viewer to display all the documents from a specific collection
        recommendedBooks.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false));

        // Initialise variables
        recommendedModelList = new ArrayList<>();
        recommendedAdapter = new RecommendedAdapter(getActivity(), recommendedModelList);
        recommendedBooks.setAdapter(recommendedAdapter);

        // Connect to data base in order to get all the documents from the collection
        db.collection("Recommended_books")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()){
                        for (QueryDocumentSnapshot doc : Objects.requireNonNull(task.getResult())){
                            RecommendedModel rec = doc.toObject(RecommendedModel.class);
                            recommendedModelList.add(rec);
                            recommendedAdapter.notifyDataSetChanged();

                        }
                    }
                    else{

                        Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                    }
                });

        // Search view
        RecyclerView recyclerViewSearchAll = root.findViewById(R.id.search_rec);
        // Initialise variables
        viewAllModelList = new ArrayList<>();
        viewAllAdapter = new ViewAllAdapter(getContext(), viewAllModelList);
        recyclerViewSearchAll.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerViewSearchAll.setAdapter(viewAllAdapter);
        recyclerViewSearchAll.setHasFixedSize(true);

        // Create listener for search bar when the text the user inserts change
        searchBar.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            // Method to clear the list and search the input
            public void afterTextChanged(Editable s) {

                if(s.toString().isEmpty()) {
                    viewAllModelList.clear();
                    viewAllAdapter.notifyDataSetChanged();
                }
                else{
                    Search(s.toString());
                }
            }
        });

        return root;
    }

    // Method to filter all documents based on the title
    @SuppressLint("NotifyDataSetChanged")
    private void Search(String title) {

        if(!title.isEmpty()){
            // Connect to data base in order to get all the documents from the collection
            db.collection("All_categories")
                    .orderBy("title")
                    .startAt(title)
                    .endAt(title + "\uf8ff")
                    .get()
                    .addOnCompleteListener(task -> {
                        if(task.isSuccessful() && task.getResult() != null){
                            viewAllModelList.clear();
                            viewAllAdapter.notifyDataSetChanged();

                            for (QueryDocumentSnapshot doc : Objects.requireNonNull(task.getResult())){
                                ViewAllModel viewAllModel = doc.toObject(ViewAllModel.class);
                                viewAllModelList.add(viewAllModel);
                                viewAllAdapter.notifyDataSetChanged();
                            }
                        }
                    });
        }
    }

    // Method to check if all permissions allowed
    private final ActivityResultLauncher<String> permissionResult = registerForActivityResult(
            new ActivityResultContracts.RequestPermission(),
            result -> {
                if(result)
                {
                    Toast.makeText(requireContext(), "Permission given", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(requireContext(), "Permission denied", Toast.LENGTH_LONG).show();
                }
            }
    );
}

