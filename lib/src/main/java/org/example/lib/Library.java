package org.example.lib;

import com.google.common.collect.ImmutableSet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

public class Library {
    private static final Logger LOG = LoggerFactory.getLogger(Library.class);

    public Set<String> someLibraryMethod() {
        LOG.info("someLibraryMethod()");
        return ImmutableSet.of("foo", "bar");
    }
}
