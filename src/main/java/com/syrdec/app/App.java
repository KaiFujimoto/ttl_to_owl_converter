package com.syrdec.app;

import java.io.*;
import org.semanticweb.owlapi.apibinding.*;
import org.semanticweb.owlapi.model.*;
import org.semanticweb.owlapi.formats.*;

/**
 * Hello world!
 * mvn exec:java "-Dexec.mainClass=com.syrdec.app.App" "-Dexec.args=/Users/kaifujimoto/Desktop/rdf_lib/offprod_DrawingSoftware_preprocessed_kg.ttl"
 *
 */


public class App
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        for(int i = 0; i<args.length; i++) {

            System.out.println("args[" + i + "]: " + args[i]);
        }
        OWLOntologyManager manager = OWLManager.createOWLOntologyManager();

        try {
            OWLOntology ontology = manager.loadOntologyFromOntologyDocument(new File(args[0])); // will take in the first argument from the command line
            System.out.println("Number of axioms: " + ontology.getAxiomCount());
            File outputFile = new File(args[1]);
            OWLDocumentFormat inputFormat = manager.getOntologyFormat(ontology);
            OWLDocumentFormat outputFormat = new OWLXMLDocumentFormat();
            if (inputFormat.isPrefixOWLDocumentFormat() && outputFormat.isPrefixOWLDocumentFormat()) {
                outputFormat.asPrefixOWLDocumentFormat().copyPrefixesFrom(inputFormat.asPrefixOWLDocumentFormat());
            }
            manager.saveOntology(ontology, outputFormat, IRI.create(outputFile.toURI()));
        } catch (Exception e) {
            System.out.println("Something went wrong.");
        } finally {
            System.out.println("The 'try catch' is finished.");
        }

    }
}
