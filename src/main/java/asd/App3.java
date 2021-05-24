package asd;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.deeplearning4j.nn.modelimport.keras.Hdf5Archive;
import org.nd4j.autodiff.samediff.SameDiff;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.indexing.NDArrayIndex;

public class App3 {
    public static void main(String[] args) throws Exception {
        SameDiff sd = SameDiff.importFrozenTF(new File("../automatic-ecg-diagnosis/freeze_session.pb"));
        System.out.println(sd.summary());
        System.out.println(sd.inputs());
        Hdf5Archive hdf5Archive = new Hdf5Archive("../automatic-ecg-diagnosis/data/ecg_tracings.hdf5");
        System.out.println(hdf5Archive.getDataSets());
        INDArray tracings = hdf5Archive.readDataSet("tracings");
        // INDArray asd = tracings.get(NDArrayIndex.point(18), NDArrayIndex.all(), NDArrayIndex.all());
        // System.out.println(Arrays.toString(asd.shape()));
        Map<String, INDArray> out = sd.batchOutput().input("signal", tracings).output("dense_8/Sigmoid").output();
        for (Entry<String, INDArray> kv : out.entrySet()) {
            if (!kv.getKey().startsWith("dense")) {
                continue;
            }
            System.out.println(kv.getKey());
            System.out.println(kv.getValue());
        }
        System.out.println("dense_8/Sigmoid");
        System.out.println(out.get("dense_8/Sigmoid").toStringFull());
    }
}
    