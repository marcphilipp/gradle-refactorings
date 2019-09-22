package org.example.cli;

import org.example.lib.Library;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class App {
    private static final Logger LOG = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        var strings = new Library().someLibraryMethod();
        LOG.debug("strings = {}", strings);
        System.out.println(strings);
    }
}
