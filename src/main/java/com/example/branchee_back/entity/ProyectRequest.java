package com.example.branchee_back.entity;
import java.util.List;

public class ProyectRequest {
    private Proyecto proyecto;
    private List<Integer> selectedUserIds;

    // Getters y setters
    public Proyecto getProyecto() {
        return proyecto;
    }

    public void setProyecto(Proyecto proyecto) {
        this.proyecto = proyecto;
    }

    public List<Integer> getSelectedUserIds() {
        return selectedUserIds;
    }

    public void setSelectedUserIds(List<Integer> selectedUserIds) {
        this.selectedUserIds = selectedUserIds;
    }
}
