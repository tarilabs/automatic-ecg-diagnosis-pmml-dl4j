package asd;

import java.io.File;
import java.util.List;

import org.jpmml.evaluator.Evaluator;
import org.jpmml.evaluator.InputField;
import org.jpmml.evaluator.LoadingModelEvaluatorBuilder;
import org.jpmml.evaluator.OutputField;
import org.jpmml.evaluator.TargetField;

public class App {
    public static void main(String[] args) throws Exception {
        // Building a model evaluator from a PMML file
Evaluator evaluator = new LoadingModelEvaluatorBuilder()
.load(new File("model.pmml"))
.build();

// Perforing the self-check
evaluator.verify();

// Printing input (x1, x2, .., xn) fields
List<? extends InputField> inputFields = evaluator.getInputFields();
System.out.println("Input fields: " + inputFields);

// Printing primary result (y) field(s)
List<? extends TargetField> targetFields = evaluator.getTargetFields();
System.out.println("Target field(s): " + targetFields);

// Printing secondary result (eg. probability(y), decision(y)) fields
List<? extends OutputField> outputFields = evaluator.getOutputFields();
System.out.println("Output fields: " + outputFields);
    }
}
    