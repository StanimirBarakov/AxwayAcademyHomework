package com.axway.academy.model.entities;

import com.axway.academy.utils.Operation;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "action_records")
public class ActionRecord {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "transfered_object")
    private String transferedObject;
    @Basic(optional = false)
    @Column(name = "time", insertable = false, updatable = false)
    private LocalDateTime time;
    @Column(name = "operation_type")
    @Enumerated(EnumType.STRING)
    private Operation operation;
    @Column(name = "file_size")
    private long fileSize;

    public ActionRecord() {
    }

    public ActionRecord(String transferedObject, Operation operation, long fileSize) {
        this.transferedObject = transferedObject;
        this.time = LocalDateTime.now();
        this.operation = operation;
        this.fileSize = fileSize;
    }

    public int getId() {
        return id;
    }

    public String getTransferedObject() {
        return transferedObject;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public Operation getOperation() {
        return operation;
    }

    public long getFileSize() {
        return fileSize;
    }

}
