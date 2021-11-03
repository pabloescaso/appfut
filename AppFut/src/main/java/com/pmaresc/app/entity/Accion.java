package com.pmaresc.app.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "acciones")
public class Accion implements Serializable{
	
	private static final long serialVersionUID = -643767275821229114L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idAccion;
	
	@Column(length = 50)
    private String team;
    private String typeAccion;
    private String subtype;
    private Integer periodAccion;
    private Long startFrame;
    private Double startTime;
    private Long endFrame;
    private Double endTime;
    private String fromP;
    private String toP;
    private Float startX;
    private Float startY;
    private Float endX;
    private Float endY;
    
    @ManyToOne
    private Partido idPartido;

    public Accion(Long idAccion, String team, String typeAccion, String subtype, Integer periodAccion, Long startFrame, Double startTime, Long endFrame, Double endTime, String fromP, String toP, Float startX, Float startY, Float endX, Float endY/*, Long idPartido*/) {
        
    	super();
    	this.idAccion = idAccion;
        this.team = team;
        this.typeAccion = typeAccion;
        this.subtype = subtype;
        this.periodAccion = periodAccion;
        this.startFrame = startFrame;
        this.startTime = startTime;
        this.endFrame = endFrame;
        this.endTime = endTime;
        this.fromP = fromP;
        this.toP = toP;
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        //this.idPartido = idPartido;
    }

    public Accion(String team, String typeAccion, String subtype, Integer periodAccion, Long startFrame, Double startTime, Long endFrame, Double endTime, String fromP, String toP, Float startX, Float startY, Float endX, Float endY/*, Long idPartido*/) {
        
    	super();
    	this.team = team;
        this.typeAccion = typeAccion;
        this.subtype = subtype;
        this.periodAccion = periodAccion;
        this.startFrame = startFrame;
        this.startTime = startTime;
        this.endFrame = endFrame;
        this.endTime = endTime;
        this.fromP = fromP;
        this.toP = toP;
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
        //this.idPartido = idPartido;
    }

    public Accion() {

    }

    public Long getIdAccion() {
        return idAccion;
    }

    public void setIdAccion(Long idAccion) {
        this.idAccion = idAccion;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }

    public String getTypeAccion() {
        return typeAccion;
    }

    public void setTypeAccion(String typeAccion) {
        this.typeAccion = typeAccion;
    }

    public String getSubtype() {
        return subtype;
    }

    public void setSubtype(String subtype) {
        this.subtype = subtype;
    }

    public Integer getPeriodAccion() {
        return periodAccion;
    }

    public void setPeriodAccion(Integer periodAccion) {
        this.periodAccion = periodAccion;
    }

    public Long getStartFrame() {
        return startFrame;
    }

    public void setStartFrame(Long startFrame) {
        this.startFrame = startFrame;
    }

    public Double getStartTime() {
        return startTime;
    }

    public void setStartTime(Double startTime) {
        this.startTime = startTime;
    }

    public Long getEndFrame() {
        return endFrame;
    }

    public void setEndFrame(Long endFrame) {
        this.endFrame = endFrame;
    }

    public Double getEndTime() {
        return endTime;
    }

    public void setEndTime(Double endTime) {
        this.endTime = endTime;
    }

    public String getFromP() {
        return fromP;
    }

    public void setFromP(String fromP) {
        this.fromP = fromP;
    }

    public String getToP() {
        return toP;
    }

    public void setToP(String toP) {
        this.toP = toP;
    }

    public Float getStartX() {
        return startX;
    }

    public void setStartX(Float startX) {
        this.startX = startX;
    }

    public Float getStartY() {
        return startY;
    }

    public void setStartY(Float startY) {
        this.startY = startY;
    }

    public Float getEndX() {
        return endX;
    }

    public void setEndX(Float endX) {
        this.endX = endX;
    }

    public Float getEndY() {
        return endY;
    }

    public void setEndY(Float endY) {
        this.endY = endY;
    }

    public Partido getIdPartido() {
        return idPartido;
    }

    public void setIdPartido(Partido idPartido) {
        this.idPartido = idPartido;
    }

}
