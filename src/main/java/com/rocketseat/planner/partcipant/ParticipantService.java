package com.rocketseat.planner.partcipant;

import com.rocketseat.planner.trip.Trip;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ParticipantService {

    @Autowired
    private ParticipantRepository repository;

    public void registerParticipantsToEvent(List<String> participantsToInvite, Trip trip){
        List<Participant> participants = participantsToInvite.stream().map(email -> new Participant(email, trip)).toList();

        System.out.println();

        this.repository.saveAll(participants);

        System.out.println(participants.getFirst().getId());
    }

    public ParticipantCreateResponse registerParticipantToEvent(String email, Trip trip){
        Participant newParticipant = new Participant(email, trip);

        System.out.println();

        this.repository.save(newParticipant);

        return new ParticipantCreateResponse(newParticipant.getId());
    }

    //This is for all the participants already in the trip
    public void triggerConfirmationEmailToParticipants(UUID tripId){

    }

    //This is for the participant
    public void triggerConfirmationEmailToParticipant(String email){

    }

    public List<ParticipantData> getAllParticipantsFromEvent(UUID tripId){
        return this.repository.findByTripId(tripId).stream().map(participant -> new ParticipantData( participant.getId(), participant.getName(), participant.getEmail(), participant.getIsConfirmed())).toList();
    }
}
