package com.learnreactiveprogramming.service;

import org.junit.jupiter.api.Test;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;

class FluxAndMonoGeneratorServiceTest {
    FluxAndMonoGeneratorService fluxAndMonoGeneratorService = new FluxAndMonoGeneratorService();

    @Test
    void nameFluxTest() {

        var namesFlux = fluxAndMonoGeneratorService.namesFlux();

        StepVerifier.create(namesFlux)
//                .expectNext("alex", "thomas", "randy")
//                .expectNext("alex", "thomas", "randy")
                .expectNextCount(3)
                .verifyComplete();

    }

    @Test
    void nameFluxMapTest() {

        var namesFlux = fluxAndMonoGeneratorService.namesFluxMap();

        StepVerifier.create(namesFlux)
                .expectNext("ALEX", "THOMAS", "RANDY")
//                .expectNext("alex", "thomas", "randy")
//                .expectNextCount(3)
                .verifyComplete();

    }

    @Test
    void namesFluxImmutability() {
        var namesFlux = fluxAndMonoGeneratorService.namesFluxImmutability();

        StepVerifier.create(namesFlux)
                .expectNext("ALEX", "THOMAS", "RAY")
                .verifyComplete();

    }

    @Test
    void namesFluxFilter() {
        var namesFlux = fluxAndMonoGeneratorService.namesFluxFilter();

        StepVerifier.create(namesFlux)
                .expectNext("randy")
                .verifyComplete();
    }

    @Test
    void namesFluxFlatMap() {
        int stringLength = 3;
        var namesFlux = fluxAndMonoGeneratorService.namesFluxFlatMap(stringLength);

        StepVerifier.create(namesFlux)
                .expectNext("A", "L", "E", "X", "T", "H", "O", "M", "A", "S")
                .verifyComplete();
    }

    @Test
    void splitStringWithDelay() {
        int stringLength = 3;
        var namesFlux = fluxAndMonoGeneratorService.namesFluxFlatMapAsync(stringLength);

        StepVerifier.create(namesFlux)
                .expectNext("A", "L", "E", "X", "T", "H", "O", "M", "A", "S")
                .verifyComplete();
    }
}