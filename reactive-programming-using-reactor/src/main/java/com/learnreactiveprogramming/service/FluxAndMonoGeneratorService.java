package com.learnreactiveprogramming.service;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class FluxAndMonoGeneratorService {

    public Flux<String> namesFlux() {
        return Flux.fromIterable(List.of("alex", "thomas", "randy"))
                .log();   // db or remote service call
    }

    public Flux<String> namesFluxMap() {
        return Flux.fromIterable(List.of("alex", "thomas", "randy"))
                .map(String::toUpperCase)
                .log();   // db or remote service call
    }

    public Flux<String> namesFluxImmutability() {
        var namesFlux = Flux.fromIterable(List.of("alex", "thomas", "randy"));
        namesFlux.map(String::toUpperCase);
        return namesFlux;
    }

    public Flux<String> namesFluxFlatMap(int stringLength) {
        return Flux.fromIterable(List.of("alex", "thomas", "ra"))
                .map(String::toUpperCase)
                .filter(s->s.length() > stringLength)
                .flatMap(s -> splitString(s))
                .log();   // db or remote service call
    }

    public Flux<String> namesFluxFlatMapAsync(int stringLength) {
        return Flux.fromIterable(List.of("alex", "thomas", "ra"))
                .map(String::toUpperCase)
                .filter(s->s.length() > stringLength)
                .flatMap(s -> splitStringWithDelay(s))
                .log();   // db or remote service call
    }

    public Flux<String> splitString(String name) {
        var charArray = name.split("");
        return Flux.fromArray(charArray);
    }

    public Flux<String> splitStringWithDelay(String name) {
        var charArray = name.split("");
        var delay = new Random().nextInt(1000);
        return Flux.fromArray(charArray).delayElements(Duration.ofMillis(delay));
    }

    public Flux<String> namesFluxFilter() {
        return Flux.fromIterable(List.of("alex", "thomas", "randy"))
                .filter( s -> s.startsWith("r"))
                .map(s -> s + " is starts with \"r\"")
                .log();   // db or remote service call
    }

    public Mono<String> nameMono() {
        return Mono.just("slash").log();
    }


    public static void main(String[] args) {
        FluxAndMonoGeneratorService fluxAndMonoGeneratorService =
                new FluxAndMonoGeneratorService();
        fluxAndMonoGeneratorService.namesFluxMap()
                .subscribe(name -> {
                    System.out.println("Name is : " + name);
                });

        fluxAndMonoGeneratorService.namesFluxFilter()
                .subscribe(name -> {
                    System.out.println("## Name is : " + name);
                });

        fluxAndMonoGeneratorService.nameMono()
                .subscribe(n -> {
                    System.out.println("Mono name is : " + n);
                });

    }
}
