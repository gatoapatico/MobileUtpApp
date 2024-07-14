package com.example.mobileutpapp.view;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mobileutpapp.entity.Plato;
import com.example.mobileutpapp.model.PlatoModel;

import java.util.List;

public class PlatoViewModel extends AndroidViewModel {
    private PlatoModel platoModel;
    private MutableLiveData<List<Plato>> platosLiveData;

    public PlatoViewModel(Application application) {
        super(application);
        platoModel = new PlatoModel(application);
        platosLiveData = new MutableLiveData<>();
        loadPlatos();
    }

    private void loadPlatos() {
        List<Plato> platos = platoModel.getAllPlatos();
        platosLiveData.postValue(platos);
    }

    public LiveData<List<Plato>> getAllPlatos() {
        return platosLiveData;
    }

    public void addPlato(Plato plato) {
        platoModel.addPlato(plato);
        loadPlatos();
    }

    public void deletePlato(int id) {
        platoModel.deletePlato(id);
        loadPlatos();
    }
}
