package org.citycult.backend.ws;

import org.citycult.backend.ws.provider.*;
import org.citycult.backend.ws.resource.RSMovie;
import org.citycult.backend.ws.resource.event.*;
import org.citycult.backend.ws.resource.venue.*;

import java.util.HashSet;
import java.util.Set;

/**
 * @author cpieloth
 */
public class CityCultApp extends javax.ws.rs.core.Application {

    private Set<Object> singletons = new HashSet<Object>();
    private Set<Class<?>> classes = new HashSet<Class<?>>();

    public CityCultApp() {
        initProducer();
        initResources();
    }

    @Override
    public Set<java.lang.Class<?>> getClasses() {
        return classes;
    }

    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }

    private void initProducer() {
        singletons.add(new EventProducer());
        singletons.add(new EventListProducer());
        singletons.add(new EventCinemaProducer());
        singletons.add(new EventCinemaListProducer());
        // NOTE: Add Producer for Sub Events, if special serialization is needed!

        singletons.add(new MovieProducer());
        singletons.add(new MovieListProducer());

        singletons.add(new VenueProducer());
        singletons.add(new VenueListProducer());
    }

    private void initResources() {
        classes.add(RSEvent.class);
//        classes.add(RSEventArtCulture.class);
//        classes.add(RSEventCinema.class);
//        classes.add(RSEventLive.class);
//        classes.add(RSEventNightlife.class);

        classes.add(RSMovie.class);

        classes.add(RSVenue.class);
        classes.add(RSVenueArtCulture.class);
        classes.add(RSVenueCinema.class);
        classes.add(RSVenueLive.class);
        classes.add(RSVenueNightlife.class);
    }

}
