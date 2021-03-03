package com.example.projet_major_simon.transfer;

import java.util.Date;
import java.util.List;

public class TaskDetailResponse {
    public Long id;

    // données
    public String name;
    public Date deadLine;

    // Liste de tous les événements de progression pour affichage dans l'ordre chrono inverse
    public List<ProgressEvent> events;

    // valeurs calculées sur le serveur à afficher au détail
    public int percentageDone;
    public int percentageTimeSpent;
}
