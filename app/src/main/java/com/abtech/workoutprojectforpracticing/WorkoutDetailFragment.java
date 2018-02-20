package com.abtech.workoutprojectforpracticing;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class WorkoutDetailFragment extends Fragment {

    private long workoutId;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(savedInstanceState !=null){
            workoutId = savedInstanceState.getLong("workoutId");
        }else {
            FragmentTransaction fragmentTransaction = getChildFragmentManager().beginTransaction();
        StopWatchFragment stopWatchFragment = new StopWatchFragment();
        fragmentTransaction.replace(R.id.stopwatch_container, stopWatchFragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
        fragmentTransaction.commit();
        }
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_workout_detail, container, false);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState){
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putLong("workoutId",workoutId);
    }



    @Override
    public  void onStart(){
        super.onStart();
        View view = getView();
        if(view != null){
            TextView title = view.findViewById(R.id.textTitle);
            Workout workout = Workout.workouts[(int)workoutId];
            title.setText(workout.getName());
            TextView description = view.findViewById(R.id.textDescription);
            description.setText(workout.getDescription());
        }
    }

    public void setWorkout(long id){
        this.workoutId = id;
    }

}
