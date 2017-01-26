///////////////////////////////////////////////////////////////////////////////
// For information as to what this class does, see the Javadoc, below.       //
// Copyright (C) 1998, 1999, 2000, 2001, 2002, 2003, 2004, 2005, 2006,       //
// 2007, 2008, 2009, 2010, 2014, 2015 by Peter Spirtes, Richard Scheines, Joseph   //
// Ramsey, and Clark Glymour.                                                //
//                                                                           //
// This program is free software; you can redistribute it and/or modify      //
// it under the terms of the GNU General Public License as published by      //
// the Free Software Foundation; either version 2 of the License, or         //
// (at your option) any later version.                                       //
//                                                                           //
// This program is distributed in the hope that it will be useful,           //
// but WITHOUT ANY WARRANTY; without even the implied warranty of            //
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the             //
// GNU General Public License for more details.                              //
//                                                                           //
// You should have received a copy of the GNU General Public License         //
// along with this program; if not, write to the Free Software               //
// Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA //
///////////////////////////////////////////////////////////////////////////////

package edu.cmu.tetrad.test;

import edu.cmu.tetrad.algcomparison.Comparison;
import edu.cmu.tetrad.algcomparison.algorithm.Algorithms;
import edu.cmu.tetrad.algcomparison.algorithm.multi.CcdMaxConcatenated;
import edu.cmu.tetrad.algcomparison.algorithm.multi.FangConcatenated;
import edu.cmu.tetrad.algcomparison.algorithm.oracle.pag.CcdMax;
import edu.cmu.tetrad.algcomparison.graph.Cyclic;
import edu.cmu.tetrad.algcomparison.independence.FisherZ;
import edu.cmu.tetrad.algcomparison.independence.SemBicTest;
import edu.cmu.tetrad.algcomparison.simulation.LinearFisherModel;
import edu.cmu.tetrad.algcomparison.simulation.LoadContinuousDataAndSingleGraph;
import edu.cmu.tetrad.algcomparison.simulation.Simulations;
import edu.cmu.tetrad.algcomparison.statistic.*;
import edu.cmu.tetrad.util.Parameters;

/**
 * An example script to simulate data and run a comparison analysis on it.
 *
 * @author jdramsey
 */
public class TestCcd {

    public void test0() {
        Parameters parameters = new Parameters();

        parameters.set("numRuns", 5);
        parameters.set("sampleSize", 1000);
        parameters.set("avgDegree", 2);
        parameters.set("numMeasures", 50);
        parameters.set("maxDegree", 1000);
        parameters.set("maxIndegree", 1000);
        parameters.set("maxOutdegree", 1000);

        parameters.set("coefLow", .2);
        parameters.set("coefHigh", .6);
        parameters.set("varLow", .2);
        parameters.set("varHigh", .4);
        parameters.set("coefSymmetric", true);
        parameters.set("probCycle", 1.0);
        parameters.set("probTwoCycle", .2);
        parameters.set("intervalBetweenShocks", 1);
        parameters.set("intervalBetweenRecordings", 1);

        parameters.set("alpha", 0.001);
        parameters.set("depth", 4);
        parameters.set("orientVisibleFeedbackLoops", true);
        parameters.set("doColliderOrientation", true);
        parameters.set("useMaxPOrientationHeuristic", true);
        parameters.set("maxPOrientationMaxPathLength", 3);
        parameters.set("applyR1", true);
        parameters.set("orientTowardDConnections", true);
        parameters.set("gaussianErrors", false);
        parameters.set("assumeIID", false);
        parameters.set("collapseTiers", true);

        Statistics statistics = new Statistics();

        statistics.add(new ParameterColumn("avgDegree"));
        statistics.add(new ParameterColumn("numMeasures"));
        statistics.add(new AdjacencyPrecision());
        statistics.add(new AdjacencyRecall());
        statistics.add(new ArrowheadPrecision());
        statistics.add(new ArrowheadRecall());
        statistics.add(new TwoCycleFalsePositive());
        statistics.add(new TwoCycleRecall());
        statistics.add(new ElapsedTime());

        Simulations simulations = new Simulations();
        simulations.add(new LinearFisherModel(new Cyclic()));

        Algorithms algorithms = new Algorithms();
        algorithms.add(new CcdMax(new FisherZ()));

        Comparison comparison = new Comparison();

        comparison.setShowAlgorithmIndices(false);
        comparison.setShowSimulationIndices(false);
        comparison.setSortByUtility(false);
        comparison.setShowUtilities(false);
        comparison.setParallelized(false);
        comparison.setSaveGraphs(true);

        comparison.setTabDelimitedTables(false);

        comparison.compareFromSimulations("comparison", simulations, algorithms, statistics, parameters);
//        comparison.compareFromFiles("comparison", algorithms, statistics, parameters);
//        comparison.saveToFiles("comparison", new LinearFisherModel(new RandomForward()), parameters);


    }

    public void TestFang() {
        Parameters parameters = new Parameters();

        parameters.set("numLags", 1);

//        parameters.set("alpha", .001);
        parameters.set("collapseTiers", true);
        parameters.set("penaltyDiscount", 1);

        parameters.set("numRandomSelections", 2);
        parameters.set("randomSelectionSize", 10);

        parameters.set("depth", 4);
        parameters.set("orientVisibleFeedbackLoops", true);
        parameters.set("doColliderOrientation", true);
        parameters.set("useMaxPOrientationHeuristic", false);
        parameters.set("maxPOrientationMaxPathLength", 3);
        parameters.set("applyR1", true);
        parameters.set("orientTowardDConnections", false);

        parameters.set("Structure", "Placeholder");

        Statistics statistics = new Statistics();

        statistics.add(new ParameterColumn("Structure"));
        statistics.add(new AdjacencyPrecision());
        statistics.add(new AdjacencyRecall());
        statistics.add(new ArrowheadPrecision());
        statistics.add(new ArrowheadRecall());
        statistics.add(new TwoCyclePrecision());
        statistics.add(new TwoCycleRecall());
        statistics.add(new TwoCycleFalsePositive());
        statistics.add(new ElapsedTime());

        Simulations simulations = new Simulations();

//        simulations.add(new LoadContinuousDataAndSingleGraph(
//                "/Users/jdramsey/Downloads/Cycles_Data_fMRI-selected/Structure1_amp"));
//        simulations.add(new LoadContinuousDataAndSingleGraph(
//                "/Users/jdramsey/Downloads/Cycles_Data_fMRI-selected/Structure1_contr"));
//        simulations.add(new LoadContinuousDataAndSingleGraph(
//                "/Users/jdramsey/Downloads/Cycles_Data_fMRI-selected/Structure2_amp"));
//        simulations.add(new LoadContinuousDataAndSingleGraph(
//                "/Users/jdramsey/Downloads/Cycles_Data_fMRI-selected/Structure2_contr"));
//        simulations.add(new LoadContinuousDataAndSingleGraph(
//                "/Users/jdramsey/Downloads/Cycles_Data_fMRI-selected/Structure3_amp_amp"));
//        simulations.add(new LoadContinuousDataAndSingleGraph(
//                "/Users/jdramsey/Downloads/Cycles_Data_fMRI-selected/Structure3_amp_contr"));
//        simulations.add(new LoadContinuousDataAndSingleGraph(
//                "/Users/jdramsey/Downloads/Cycles_Data_fMRI-selected/Structure3_contr_amp"));
//        simulations.add(new LoadContinuousDataAndSingleGraph(
//                "/Users/jdramsey/Downloads/Cycles_Data_fMRI-selected/Structure4_amp_amp"));
//        simulations.add(new LoadContinuousDataAndSingleGraph(
//                "/Users/jdramsey/Downloads/Cycles_Data_fMRI-selected/Structure4_amp_contr"));
//        simulations.add(new LoadContinuousDataAndSingleGraph(
//                "/Users/jdramsey/Downloads/Cycles_Data_fMRI-selected/Structure4_contr_amp"));
//        simulations.add(new LoadContinuousDataAndSingleGraph(
//                "/Users/jdramsey/Downloads/Cycles_Data_fMRI-selected/Structure5_amp"));
//        simulations.add(new LoadContinuousDataAndSingleGraph(
//                "/Users/jdramsey/Downloads/Cycles_Data_fMRI-selected/Structure5_contr"));
//
//        simulations.add(new LoadContinuousDataAndSingleGraph(
//                "/Users/jdramsey/Downloads/Cycles_Data_fMRI-selected/Structure2_amp_c4"));
//        simulations.add(new LoadContinuousDataAndSingleGraph(
//                "/Users/jdramsey/Downloads/Cycles_Data_fMRI-selected/Structure2_contr_c4"));
//
//        simulations.add(new LoadContinuousDataAndSingleGraph(
//                "/Users/jdramsey/Downloads/Cycles_Data_fMRI-selected/Structure2_contr_p2n6"));
//        simulations.add(new LoadContinuousDataAndSingleGraph(
//                "/Users/jdramsey/Downloads/Cycles_Data_fMRI-selected/Structure2_contr_p6n2"));


        simulations.add(new LoadContinuousDataAndSingleGraph(
                "/Users/jdramsey/Downloads/Cycles_Data_fMRI-selected/ComplexMatrix_1"));

        Algorithms algorithms = new Algorithms();
        algorithms.add(new FangConcatenated(new SemBicTest()));

        Comparison comparison = new Comparison();

        comparison.setShowAlgorithmIndices(false);
        comparison.setShowSimulationIndices(true);
        comparison.setSortByUtility(false);
        comparison.setShowUtilities(false);
        comparison.setParallelized(false);
        comparison.setSaveGraphs(true);

        comparison.setTabDelimitedTables(false);

        comparison.compareFromSimulations("comparison", simulations, algorithms, statistics, parameters);
//        comparison.compareFromFiles("comparison", algorithms, statistics, parameters);
//        comparison.saveToFiles("comparison", new LinearFisherModel(new RandomForward()), parameters);

    }

    public static void main(String... args) {
        new TestCcd().TestFang();
    }
}




