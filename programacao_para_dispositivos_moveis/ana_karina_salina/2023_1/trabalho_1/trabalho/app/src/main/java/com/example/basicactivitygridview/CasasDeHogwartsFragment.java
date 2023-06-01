package com.example.basicactivitygridview;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.basicactivitygridview.databinding.FragmentCasasDeHogwartsBinding;

public class CasasDeHogwartsFragment extends Fragment {

    private FragmentCasasDeHogwartsBinding binding;
    private MediaPlayer mediaPlayer;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentCasasDeHogwartsBinding.inflate(inflater, container,
                false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.buttonCasasDeHogwarts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(CasasDeHogwartsFragment.this)
                        .navigate(R.id.action_CasasDeHogwartsFragment_to_HomeFragment);
            }
        });
        int[] list=new int[]{R.drawable.sample_0,R.drawable.sample_1,
                R.drawable.sample_2,R.drawable.sample_3};
        binding.gridCasasDeHogwarts.setAdapter(new Adaptador(getContext(),list));
        binding.gridCasasDeHogwarts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent,
                                    View view, int position, long id) {
                if(mediaPlayer!=null){
                    mediaPlayer.release();
                }
                switch (position){
                    case 0:
                        binding.txtImagem.setText("Casa: Grifinória");
                        mediaPlayer= MediaPlayer.create(getContext(),R.raw.gryffindor);
                        break;
                    case 1:
                        binding.txtImagem.setText("Casa: Corvinal");
                        mediaPlayer=MediaPlayer.create(getContext(),R.raw.ravenclaw);
                        break;
                    case 2:
                        binding.txtImagem.setText("Casa: Sonserina");
                        mediaPlayer=MediaPlayer.create(getContext(),R.raw.slytherin);
                        break;
                    case 3:
                        binding.txtImagem.setText("Casa: Lula-Lufa");
                        mediaPlayer=MediaPlayer.create(getContext(),R.raw.hufflepuff);
                        break;

                }
                if(mediaPlayer!=null){
                    mediaPlayer.start();
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            mediaPlayer.release();
                        }
                    });
                }

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}