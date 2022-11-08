package com.ruoyi.system.domain.AssemblyPojo.Knowledge.MaterialKnowledge;

import org.springframework.data.neo4j.core.schema.GeneratedValue;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

import java.util.Objects;

@Node("AnalysisSpectrogram")
public class AnalysisSpectrogram {

    @Id
    @GeneratedValue
    private Long spectrogramId;

    @Property(name = "label")
    private String spectrogramName;

    @Property(name = "InfraredSpectrum")
    private String infraredSpectrumUrl;

    @Property(name = "ThermalSpectrogram")
    private String thermalSpectrogramUrl;

    @Property(name = "NmrSpectrum")
    private String nmrSpectrumUrl;

    @Property(name = "XraySpectrum")
    private String XraySpectrumUrl;

    @Property(name = "RamanSpectrum")
    private String ramanSpectrumUrl;

    @Property(name = "UltravioletSpectrum")
    private String ultravioletSpectrumUrl;

    @Property(name = "MassSpectrum")
    private String massSpectrumUrl;

    public Long getSpectrogramId() {
        return spectrogramId;
    }

    public void setSpectrogramId(Long spectrogramId) {
        this.spectrogramId = spectrogramId;
    }

    public String getSpectrogramName() {
        return spectrogramName;
    }

    public void setSpectrogramName(String spectrogramName) {
        this.spectrogramName = spectrogramName;
    }

    public String getInfraredSpectrumUrl() {
        return infraredSpectrumUrl;
    }

    public void setInfraredSpectrumUrl(String infraredSpectrumUrl) {
        this.infraredSpectrumUrl = infraredSpectrumUrl;
    }

    public String getThermalSpectrogramUrl() {
        return thermalSpectrogramUrl;
    }

    public void setThermalSpectrogramUrl(String thermalSpectrogramUrl) {
        this.thermalSpectrogramUrl = thermalSpectrogramUrl;
    }

    public String getNmrSpectrumUrl() {
        return nmrSpectrumUrl;
    }

    public void setNmrSpectrumUrl(String nmrSpectrumUrl) {
        this.nmrSpectrumUrl = nmrSpectrumUrl;
    }

    public String getXraySpectrumUrl() {
        return XraySpectrumUrl;
    }

    public void setXraySpectrumUrl(String xraySpectrumUrl) {
        XraySpectrumUrl = xraySpectrumUrl;
    }

    public String getRamanSpectrumUrl() {
        return ramanSpectrumUrl;
    }

    public void setRamanSpectrumUrl(String ramanSpectrumUrl) {
        this.ramanSpectrumUrl = ramanSpectrumUrl;
    }

    public String getUltravioletSpectrumUrl() {
        return ultravioletSpectrumUrl;
    }

    public void setUltravioletSpectrumUrl(String ultravioletSpectrumUrl) {
        this.ultravioletSpectrumUrl = ultravioletSpectrumUrl;
    }

    public String getMassSpectrumUrl() {
        return massSpectrumUrl;
    }

    public void setMassSpectrumUrl(String massSpectrumUrl) {
        this.massSpectrumUrl = massSpectrumUrl;
    }

    public AnalysisSpectrogram() {
    }

    public AnalysisSpectrogram(Long spectrogramId, String spectrogramName, String infraredSpectrumUrl, String thermalSpectrogramUrl, String nmrSpectrumUrl, String xraySpectrumUrl, String ramanSpectrumUrl, String ultravioletSpectrumUrl, String massSpectrumUrl) {
        this.spectrogramId = spectrogramId;
        this.spectrogramName = spectrogramName;
        this.infraredSpectrumUrl = infraredSpectrumUrl;
        this.thermalSpectrogramUrl = thermalSpectrogramUrl;
        this.nmrSpectrumUrl = nmrSpectrumUrl;
        XraySpectrumUrl = xraySpectrumUrl;
        this.ramanSpectrumUrl = ramanSpectrumUrl;
        this.ultravioletSpectrumUrl = ultravioletSpectrumUrl;
        this.massSpectrumUrl = massSpectrumUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnalysisSpectrogram that = (AnalysisSpectrogram) o;
        return Objects.equals(spectrogramId, that.spectrogramId) && Objects.equals(spectrogramName, that.spectrogramName) && Objects.equals(infraredSpectrumUrl, that.infraredSpectrumUrl) && Objects.equals(thermalSpectrogramUrl, that.thermalSpectrogramUrl) && Objects.equals(nmrSpectrumUrl, that.nmrSpectrumUrl) && Objects.equals(XraySpectrumUrl, that.XraySpectrumUrl) && Objects.equals(ramanSpectrumUrl, that.ramanSpectrumUrl) && Objects.equals(ultravioletSpectrumUrl, that.ultravioletSpectrumUrl) && Objects.equals(massSpectrumUrl, that.massSpectrumUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(spectrogramId, spectrogramName, infraredSpectrumUrl, thermalSpectrogramUrl, nmrSpectrumUrl, XraySpectrumUrl, ramanSpectrumUrl, ultravioletSpectrumUrl, massSpectrumUrl);
    }

    @Override
    public String toString() {
        return "AnalysisSpectrogram{" +
                "spectrogramId=" + spectrogramId +
                ", spectrogramName='" + spectrogramName + '\'' +
                ", infraredSpectrumUrl='" + infraredSpectrumUrl + '\'' +
                ", thermalSpectrogramUrl='" + thermalSpectrogramUrl + '\'' +
                ", nmrSpectrumUrl='" + nmrSpectrumUrl + '\'' +
                ", XraySpectrumUrl='" + XraySpectrumUrl + '\'' +
                ", ramanSpectrumUrl='" + ramanSpectrumUrl + '\'' +
                ", ultravioletSpectrumUrl='" + ultravioletSpectrumUrl + '\'' +
                ", massSpectrumUrl='" + massSpectrumUrl + '\'' +
                '}';
    }
}
