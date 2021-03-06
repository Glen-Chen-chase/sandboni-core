package com.sandboni.core.engine.sta.operation;

import com.sandboni.core.engine.sta.graph.Edge;
import com.sandboni.core.engine.sta.graph.vertex.CucumberVertex;
import com.sandboni.core.engine.sta.graph.vertex.TestVertex;
import com.sandboni.core.engine.sta.graph.vertex.Vertex;
import org.jgrapht.Graph;

import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

public class UnitTestsOperation extends AbstractGraphOperation<SetResult<TestVertex>> {
    private final Set<TestVertex> tests;
    private final boolean isExternal;

    UnitTestsOperation(Set<TestVertex> tests) {
        this(tests, false);
    }

    UnitTestsOperation(Set<TestVertex> tests, boolean isExternal) {
        Objects.requireNonNull(tests, INVALID_ARGUMENT);
        this.tests = tests;
        this.isExternal = isExternal;
    }

    @Override
    public SetResult<TestVertex> execute(Graph<Vertex, Edge> graph) {
        Set<TestVertex> set = tests.stream()
                .filter(v -> !(v instanceof CucumberVertex) && v.isExternalLocation() == isExternal)
                .collect(Collectors.toSet());
        return new SetResult<>(set);
    }
}
