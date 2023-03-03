package com.ruoyi.system.domain.AssemblyPojo.Process.Interface;

import java.util.List;

public interface SequenceInterface {
    Long getSequenceId();

    String getSequenceName();

    String getSequenceNumber();

    String getSequenceDescription();

    double getQuasiClosingHours();

    double getTaktTime();

    List<String> getSequenceRemark();
}
