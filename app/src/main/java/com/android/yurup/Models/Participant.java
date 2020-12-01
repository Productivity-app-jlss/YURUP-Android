package com.android.yurup.Models;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseUser;

@ParseClassName("Participant")
public class Participant extends ParseObject {
        public static final String KEY_CHALLENGE = "challenge";
        public static final String KEY_PARTICIPANT = "participant";
        public static final String KEY_STATUS = "status";

        public Participant() {
        }

        public Participant(String theClassName) {
            super(theClassName);
        }

        public ParseObject getChallengeId() {
            return getParseObject(KEY_CHALLENGE);
        }

        public void setChallengeId(ParseObject challenge){
            put(KEY_CHALLENGE, challenge);
        }

        public ParseUser getParticipantId() {
            return getParseUser(KEY_PARTICIPANT);
        }

        public void setParticipantId(ParseUser participant){
            put(KEY_PARTICIPANT, participant);
        }

        public String getStatus() {
            return getString(KEY_STATUS);
        }

        public void setStatus(String status){
            put(KEY_STATUS, status);
        }
}
