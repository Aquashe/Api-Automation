package com.api.pojo.graphQl.model;

@lombok.Data
public class Data {
    private CreateLocation createLocation;
    private DeleteLocations deleteLocations;
    private EditLocation editLocation;
    private CreateCharacter createCharacter;
    private DeleteCharacters deleteCharacters;
    private CreateEpisode createEpisode;
    private DeleteEpisodes deleteEpisodes;
    private AssociateEpisodeCharacter associateEpisodeCharacter;
}
