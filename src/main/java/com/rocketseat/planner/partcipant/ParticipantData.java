package com.rocketseat.planner.partcipant;

import java.util.UUID;

public record ParticipantData(UUID id, String name, String email,Boolean isConfirmed) {

}
