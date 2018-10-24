package com.alevel.deliverit.postal.network;

import com.alevel.deliverit.logistics.fsm.*;
import com.alevel.deliverit.logistics.postal.network.Connection;
import com.alevel.deliverit.logistics.postal.network.PostOffice;
import com.alevel.deliverit.logistics.postal.network.PostOfficeId;
import com.alevel.deliverit.logistics.postal.network.constraint.Constraint;
import com.alevel.deliverit.logistics.postal.network.constraint.SimpleConstraint;
import com.google.common.collect.ImmutableSet;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static com.alevel.deliverit.logistics.fsm.State.STANDBY;

/**
 * @author Vadym Mitin
 */
public class PostalNetworkCreator {

    private static PostOffice fakeOffice(Long id) {
        return PostOffice.builder()
                .setId(new PostOfficeId(id))
                .setName("Post unit " + id)
                .build();
    }

    private static Connection fakeConnection(PostOffice start, PostOffice end, int weight) {
        Set<Constraint> constraints = new HashSet<>();
        constraints.add(new SimpleConstraint(weight));

        return Connection.builder()
                .setStartNode(start)
                .setEndNode(end)
                .setConstraints(constraints)
                .build();
    }

    public static void buildFakeNetwork() {
        final PostNetwork postNetwork = PostNetwork.instance();

        PostOffice postOffice1 = fakeOffice(1L);
        postNetwork.addUnit(postOffice1);
        PostOffice postOffice2 = fakeOffice(2L);
        postNetwork.addUnit(postOffice2);
        PostOffice postOffice3 = fakeOffice(3L);
        postNetwork.addUnit(postOffice3);
        PostOffice postOffice4 = fakeOffice(4L);
        postNetwork.addUnit(postOffice4);
        PostOffice postOffice5 = fakeOffice(5L);
        postNetwork.addUnit(postOffice5);
        PostOffice postOffice6 = fakeOffice(6L);
        postNetwork.addUnit(postOffice6);
        PostOffice postOffice7 = fakeOffice(7L);
        postNetwork.addUnit(postOffice7);
        PostOffice postOffice8 = fakeOffice(8L);
        postNetwork.addUnit(postOffice8);
        PostOffice postOffice9 = fakeOffice(9L);
        postNetwork.addUnit(postOffice9);
        PostOffice postOffice10 = fakeOffice(10L);
        postNetwork.addUnit(postOffice10);

        Map<State, ImmutableSet<State>> routeMap = RouteMap.routeMap;
        postOffice1.setStateMachine(new FiniteStateMachineImpl<>(new LogisticCommandFactory(), routeMap, STANDBY));
        postOffice2.setStateMachine(new FiniteStateMachineImpl<>(new LogisticCommandFactory(), routeMap, STANDBY));
        postOffice3.setStateMachine(new FiniteStateMachineImpl<>(new LogisticCommandFactory(), routeMap, STANDBY));
        postOffice4.setStateMachine(new FiniteStateMachineImpl<>(new LogisticCommandFactory(), routeMap, STANDBY));
        postOffice5.setStateMachine(new FiniteStateMachineImpl<>(new LogisticCommandFactory(), routeMap, STANDBY));
        postOffice6.setStateMachine(new FiniteStateMachineImpl<>(new LogisticCommandFactory(), routeMap, STANDBY));
        postOffice7.setStateMachine(new FiniteStateMachineImpl<>(new LogisticCommandFactory(), routeMap, STANDBY));
        postOffice8.setStateMachine(new FiniteStateMachineImpl<>(new LogisticCommandFactory(), routeMap, STANDBY));
        postOffice9.setStateMachine(new FiniteStateMachineImpl<>(new LogisticCommandFactory(), routeMap, STANDBY));
        postOffice10.setStateMachine(new FiniteStateMachineImpl<>(new LogisticCommandFactory(), routeMap, STANDBY));


        postNetwork.addConnection(fakeConnection(postOffice1, postOffice2, 5));
        postNetwork.addConnection(fakeConnection(postOffice1, postOffice3, 2));

        postNetwork.addConnection(fakeConnection(postOffice2, postOffice3, 4));
        postNetwork.addConnection(fakeConnection(postOffice2, postOffice4, 2));
        postNetwork.addConnection(fakeConnection(postOffice2, postOffice6, 1));
        postNetwork.addConnection(fakeConnection(postOffice2, postOffice8, 3));

        postNetwork.addConnection(fakeConnection(postOffice3, postOffice5, 7));
        postNetwork.addConnection(fakeConnection(postOffice3, postOffice7, 8));

        postNetwork.addConnection(fakeConnection(postOffice4, postOffice1, 3));
        postNetwork.addConnection(fakeConnection(postOffice4, postOffice3, 10));
        postNetwork.addConnection(fakeConnection(postOffice4, postOffice5, 1));

        postNetwork.addConnection(fakeConnection(postOffice5, postOffice6, 1));
        postNetwork.addConnection(fakeConnection(postOffice5, postOffice7, 8));

        postNetwork.addConnection(fakeConnection(postOffice6, postOffice3, 5));
        postNetwork.addConnection(fakeConnection(postOffice6, postOffice10, 5));

        postNetwork.addConnection(fakeConnection(postOffice7, postOffice1, 6));
        postNetwork.addConnection(fakeConnection(postOffice7, postOffice10, 1));

        postNetwork.addConnection(fakeConnection(postOffice8, postOffice5, 4));
        postNetwork.addConnection(fakeConnection(postOffice8, postOffice9, 3));

        postNetwork.addConnection(fakeConnection(postOffice9, postOffice6, 5));
        postNetwork.addConnection(fakeConnection(postOffice9, postOffice10, 1));

        postNetwork.addConnection(fakeConnection(postOffice10, postOffice4, 9));
        postNetwork.addConnection(fakeConnection(postOffice10, postOffice5, 6));
        postNetwork.addConnection(fakeConnection(postOffice10, postOffice9, 2));
    }
}
