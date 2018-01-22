package com.lujian.tech.libs.graphviz.demo;

import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.engine.Rasterizer;
import guru.nidi.graphviz.model.Graph;

import java.io.File;
import java.io.IOException;

import static guru.nidi.graphviz.model.Factory.graph;
import static guru.nidi.graphviz.model.Factory.node;

public class Configuration {

    public static void main(String[] args) throws IOException {
        Graph g = graph("example5").directed().with(node("abc").link(node("xyz")));
        Graphviz viz = Graphviz.fromGraph(g);
        viz.width(200).render(Format.SVG).toFile(new File("tech-libs/src/main/resources/example/ex5.svg"));
        viz.width(200).rasterizer(Rasterizer.BATIK).render(Format.PNG).toFile(new File("tech-libs/src/main/resources/example/ex5b.png"));
        viz.width(200).rasterizer(Rasterizer.SALAMANDER).render(Format.PNG).toFile(new File("tech-libs/src/main/resources/example/ex5s.png"));
    }

}
