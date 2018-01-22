package com.lujian.tech.libs.graphviz.demo;

import guru.nidi.graphviz.engine.Format;
import guru.nidi.graphviz.engine.Graphviz;
import guru.nidi.graphviz.model.Graph;
import guru.nidi.graphviz.model.LinkSource;
import guru.nidi.graphviz.model.Node;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static guru.nidi.graphviz.model.Factory.graph;
import static guru.nidi.graphviz.model.Factory.node;

public class ListExample {

    public static void main(String[] args) throws IOException {

        Graph g = graph("list-example").directed();

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);

        g = g.with(
                createNodeList(list));

        Graphviz.fromGraph(g).width(900).render(Format.PNG).toFile(new File("tech-libs/src/main/resources/example/ex3.png"));

    }

    private static LinkSource createNodeList(List<Integer> list) {
        Node cur = node("xxxx");
        for (Integer val : list) {
            cur.link(node(String.valueOf(val)));
        }
        return cur;
    }

}
