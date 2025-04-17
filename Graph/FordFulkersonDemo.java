package Graph;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class FordFulkersonDemo {
    public static void main(String[] args) {
        Patent p1  = new Patent("US1234567A", "Method of Doing Stuff", 1980);
        Patent p2  = new Patent("US7654321B", "Device for Something",  1995);
        Patent p3  = new Patent("US1111111C", "Advanced Widget",       2001);
        Patent p4  = new Patent("US2222222A", "Improved Widget",       2010);
        Patent p5  = new Patent("US3333333B", "Another Gadget",        2015);
        Patent p6  = new Patent("US4444444A", "High-Speed Processor",  2002);
        Patent p7  = new Patent("US5555555B", "Low-Power Processor",   2005);
        Patent p8  = new Patent("US6666666C", "Portable Gadget Charger", 2008);
        Patent p9  = new Patent("US7777777A", "Robotics Arm Assembly", 2012);
        Patent p10 = new Patent("US8888888B", "Self-Driving Vehicle System", 2016);
        Patent p11 = new Patent("US9999999C", "Machine Learning Framework", 2011);
        Patent p12 = new Patent("US1010101A", "Neural Network Optimizer",   2014);
        Patent p13 = new Patent("US1212121B", "Quantum Encryption Method",  2018);
        Patent p14 = new Patent("US1313131C", "AR/VR Headset Technology",   2017);
        Patent p15 = new Patent("US1414141A", "Battery Management System",   2009);
        Patent p16 = new Patent("US1515151B", "Hydrogen Fuel Cell",          2020);
        Patent p17 = new Patent("US1616161C", "Wearable Health Monitor",     2019);
        Patent p18 = new Patent("US1717171A", "3D Printing Process",         2013);
        Patent p19 = new Patent("US1818181B", "Genome Sequencing Technique", 2021);
        Patent p20 = new Patent("US1919191C", "Blockchain Transaction Protocol", 2022);

        List<Patent> patents = Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10,
                p11, p12, p13, p14, p15, p16, p17, p18, p19, p20);

        PatentFlowNetwork graph = new PatentFlowNetwork(patents);

        Random rand = new Random();

        graph.addCitation(p1, p2, 1 + rand.nextInt(10));
        graph.addCitation(p1, p3, 1 + rand.nextInt(10));
        graph.addCitation(p2, p3, 1 + rand.nextInt(10));
        graph.addCitation(p2, p4, 1 + rand.nextInt(10));
        graph.addCitation(p4, p3, 1 + rand.nextInt(10));
        graph.addCitation(p6, p1, 1 + rand.nextInt(10));
        graph.addCitation(p6, p2, 1 + rand.nextInt(10));
        graph.addCitation(p7, p1, 1 + rand.nextInt(10));
        graph.addCitation(p7, p6, 1 + rand.nextInt(10));
        graph.addCitation(p8, p2, 1 + rand.nextInt(10));
        graph.addCitation(p8, p6, 1 + rand.nextInt(10));
        graph.addCitation(p9, p4, 1 + rand.nextInt(10));
        graph.addCitation(p9, p6, 1 + rand.nextInt(10));
        graph.addCitation(p10, p3, 1 + rand.nextInt(10));
        graph.addCitation(p10, p7, 1 + rand.nextInt(10));
        graph.addCitation(p11, p3, 1 + rand.nextInt(10));
        graph.addCitation(p11, p8, 1 + rand.nextInt(10));
        graph.addCitation(p11, p10, 1 + rand.nextInt(10));
        graph.addCitation(p12, p11, 1 + rand.nextInt(10));
        graph.addCitation(p12, p7, 1 + rand.nextInt(10));
        graph.addCitation(p13, p1, 1 + rand.nextInt(10));
        graph.addCitation(p13, p8, 1 + rand.nextInt(10));
        graph.addCitation(p13, p11, 1 + rand.nextInt(10));
        graph.addCitation(p14, p3, 1 + rand.nextInt(10));
        graph.addCitation(p14, p4, 1 + rand.nextInt(10));
        graph.addCitation(p15, p1, 1 + rand.nextInt(10));
        graph.addCitation(p15, p2, 1 + rand.nextInt(10));
        graph.addCitation(p16, p15, 1 + rand.nextInt(10));
        graph.addCitation(p16, p9, 1 + rand.nextInt(10));
        graph.addCitation(p17, p15, 1 + rand.nextInt(10));
        graph.addCitation(p18, p2, 1 + rand.nextInt(10));
        graph.addCitation(p18, p6, 1 + rand.nextInt(10));
        graph.addCitation(p18, p9, 1 + rand.nextInt(10));
        graph.addCitation(p19, p10, 1 + rand.nextInt(10));
        graph.addCitation(p19, p14, 1 + rand.nextInt(10));
        graph.addCitation(p19, p2, 1 + rand.nextInt(10));
        graph.addCitation(p19, p6, 1 + rand.nextInt(10));
        graph.addCitation(p19, p8, 1 + rand.nextInt(10));
        graph.addCitation(p19, p15, 1 + rand.nextInt(10));
        graph.addCitation(p20, p19, 1 + rand.nextInt(10));
        graph.addCitation(p20, p7, 1 + rand.nextInt(10));
        graph.addCitation(p20, p11, 1 + rand.nextInt(10));
        graph.addCitation(p20, p16, 1 + rand.nextInt(10));
        graph.addCitation(p20, p13, 1 + rand.nextInt(10));
        graph.addCitation(p5, p7, 1 + rand.nextInt(10));
        graph.addCitation(p5, p14, 1 + rand.nextInt(10));
        graph.addCitation(p1, p10, 1 + rand.nextInt(10));
        graph.addCitation(p3, p7, 1 + rand.nextInt(10));
        graph.addCitation(p9, p15, 1 + rand.nextInt(10));
        graph.addCitation(p9, p10, 1 + rand.nextInt(10));
        graph.addCitation(p11, p12, 1 + rand.nextInt(10));
        graph.addCitation(p12, p16, 1 + rand.nextInt(10));
        graph.addCitation(p14, p16, 1 + rand.nextInt(10));
        graph.addCitation(p7, p18, 1 + rand.nextInt(10));
        graph.addCitation(p13, p19, 1 + rand.nextInt(10));
        graph.addCitation(p19, p20, 1 + rand.nextInt(10));

        int sIndex = 1, tIndex = 20;
        PatentMaxFlowFordFulkerson maxflow = new PatentMaxFlowFordFulkerson(graph, patents.get(sIndex-1), patents.get(tIndex-1));

        System.out.printf("Max flow from %d to %d = %.1f\n", sIndex, tIndex, maxflow.value());

        for (int v = 0; v < graph.V(); v++) {
            for (PatentFlowNetwork.FlowEdge e : graph.adj(v)) {
                // Only print "forward" edges with capacity > 0
                if (e.capacity() > 0 && e.from() == v) {
                    System.out.println(e);
                }
            }
        }

        // Example patents
//        Patent pA = new Patent("US-A", "Alpha Gadget", 2020);
//        Patent pB = new Patent("US-B", "Beta Gadget",  2021);
//        Patent pC = new Patent("US-C", "Gamma Widget", 2022);
//        Patent pD = new Patent("US-D", "Delta Widget", 2019);
//
//        List<Patent> all = Arrays.asList(pA, pB, pC, pD);
//
//        // Build a "flow" digraph with 4 patents
//        PatentFlowNetwork g = new PatentFlowNetwork(all);
//
//        // Add edges with capacities
//        g.addCitation(pA, pB, 10.0);  // capacity 10
//        g.addCitation(pA, pC, 5.0);   // capacity 5
//        g.addCitation(pB, pC, 2.0);   // capacity 2
//        g.addCitation(pB, pD, 6.0);   // capacity 6
//        g.addCitation(pC, pD, 8.0);   // capacity 8
//
//        //
//        System.out.println(g);
//
//        //
//        PatentMaxFlowFordFulkerson maxflow = new PatentMaxFlowFordFulkerson(g, pA, pD);
//        System.out.println("Max flow from A to D = " + maxflow.value());
//
//        int aId = g.getPatentIndex().getId(pA);
//        int bId = g.getPatentIndex().getId(pB);
//        int cId = g.getPatentIndex().getId(pC);
//        int dId = g.getPatentIndex().getId(pD);
//
//        for (int v = 0; v < g.V(); v++) {
//            for (PatentFlowNetwork.FlowEdge e : g.adj(v)) {
//                if (e.capacity() > 0 && e.from() == v) {
//                    System.out.println(e);
//                }
//            }
//        }
//
//
    }
}
