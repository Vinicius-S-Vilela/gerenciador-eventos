package model;

import java.util.ArrayList;
import java.util.List;

public class Local {
    private List<String> campi;

    public Local() {
        campi = new ArrayList<>();
        campi.add("Campus Barcelona");
        campi.add("Campus Conceição");
        campi.add("Campus Centro");
        campi.add("Campus Centro II");
        campi.add("Campus São Paulo");
        campi.add("Campus Manoel Coelho");
        campi.add("Campus Itapetininga");
    }

    public List<String> getCampi() {
        return campi;
    }
}
