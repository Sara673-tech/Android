package com.example.eyedetectionapp.detection;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DetectionResponse {

    @SerializedName("eye_model_response")
    @Expose
    int eye_model_response;

    @SerializedName("conjunctivity_model_response")
    @Expose
    int conjunctivity_model_response;

    public DetectionResponse() {
    }

    public DetectionResponse(int eye_model_response, int conjunctivity_model_response) {
        this.eye_model_response = eye_model_response;
        this.conjunctivity_model_response = conjunctivity_model_response;
    }

    public int getEye_model_response() {
        return eye_model_response;
    }

    public void setEye_model_response(int eye_model_response) {
        this.eye_model_response = eye_model_response;
    }

    public int getConjunctivity_model_response() {
        return conjunctivity_model_response;
    }

    public void setConjunctivity_model_response(int conjunctivity_model_response) {
        this.conjunctivity_model_response = conjunctivity_model_response;
    }
}
