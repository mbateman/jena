/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 *     
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.jena.hadoop.rdf.io.input.readers;

import java.io.IOException;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.mapreduce.RecordReader;
import org.apache.jena.hadoop.rdf.io.registry.HadoopRdfIORegistry;
import org.apache.jena.hadoop.rdf.types.QuadWritable;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFLanguages;

import com.hp.hpl.jena.sparql.core.Quad;

/**
 * A record reader that reads triples from any RDF quads format
 */
public class QuadsReader extends AbstractRdfReader<Quad, QuadWritable> {

    @Override
    protected RecordReader<LongWritable, QuadWritable> selectRecordReader(Lang lang) throws IOException {
        if (!RDFLanguages.isQuads(lang))
            throw new IOException(
                    lang.getLabel()
                            + " is not a RDF quads format, perhaps you wanted TriplesInputFormat or TriplesOrQuadsInputFormat instead?");

        // This will throw an appropriate error if the language does not support
        // triples
        return HadoopRdfIORegistry.createQuadReader(lang);
    }

}
