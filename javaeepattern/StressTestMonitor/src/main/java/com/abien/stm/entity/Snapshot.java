package com.abien.stm.entity;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Adam Bien, blog.adam-bien.com
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlRootElement
@Entity
public class Snapshot {
    
    @Id
    @GeneratedValue
    private long id;
    @Temporal(TemporalType.TIME)
    private Date monitoringTime;
    private long usedHeapSize;
    private int threadCount;
    private int totalErrors;
    private int currentThreadBusy;
    private int committedTX;
    private int rolledBackTX;
    private int queuedConnections;

    public Snapshot(long usedHeapSize, int threadCount, int totalErrors, int currentThreadBusy, int committedTX, int rolledBackTX, int queuedConnections) {
        this();
        this.usedHeapSize = usedHeapSize;
        this.threadCount = threadCount;
        this.totalErrors = totalErrors;
        this.currentThreadBusy = currentThreadBusy;
        this.committedTX = committedTX;
        this.rolledBackTX = rolledBackTX;
        this.queuedConnections = queuedConnections;
    }

    public Snapshot() {
        this.monitoringTime = new Date();
    }
    
    public boolean isSuspicious(){
        return (currentThreadBusy > 100);
    }

    @Override
    public String toString() {
        return "Snapshot{" + "id=" + id + ", monitoringTime=" + monitoringTime + ", usedHeapSize=" + usedHeapSize + ", threadCount=" + threadCount + ", totalErrors=" + totalErrors + ", currentThreadBusy=" + currentThreadBusy + ", committedTX=" + committedTX + ", rolledBackTX=" + rolledBackTX + ", queuedConnections=" + queuedConnections + '}';
    }
}
