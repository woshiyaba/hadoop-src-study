package org.apache.hadoop.hdfs.server.datanode.metrics;

import java.util.concurrent.atomic.AtomicInteger;

import org.apache.hadoop.metrics2.annotation.Metric;
import org.apache.hadoop.metrics2.annotation.Metrics;

@Metrics(about = "Block Report Metrics", context = "dfs")
public class BlockReportStatistics {

  @Metric("Last trigger type")
  private String lastTriggerType; // HEARTBEAT/SCHEDULED/MANUAL

  @Metric("Last report block count")
  private volatile long lastBlockCount;

  @Metric("Last report duration (ms)")
  private volatile long lastDuration;

  @Metric("Pending report queue size")
  private AtomicInteger pendingQueueSize = new AtomicInteger(0);

  public String getLastTriggerType() {
    return lastTriggerType;
  }

  public void setLastTriggerType(String lastTriggerType) {
    this.lastTriggerType = lastTriggerType;
  }

  public long getLastBlockCount() {
    return lastBlockCount;
  }

  public void setLastBlockCount(long lastBlockCount) {
    this.lastBlockCount = lastBlockCount;
  }

  public long getLastDuration() {
    return lastDuration;
  }

  public void setLastDuration(long lastDuration) {
    this.lastDuration = lastDuration;
  }

  public AtomicInteger getPendingQueueSize() {
    return pendingQueueSize;
  }

  public void setPendingQueueSize(AtomicInteger pendingQueueSize) {
    this.pendingQueueSize = pendingQueueSize;
  }
}
