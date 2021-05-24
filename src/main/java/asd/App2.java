package asd;

import org.deeplearning4j.nn.graph.ComputationGraph;
import org.deeplearning4j.nn.modelimport.keras.KerasModelImport;

public class App2 {
    public static void main(String[] args) throws Exception {
        ComputationGraph model = KerasModelImport.importKerasModelAndWeights(
                "../automatic-ecg-diagnosis/dnn_predicts/model.hdf5");
        System.out.println(model);
    }
}
    