package Graph;

import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

import Graph.Patent;

public class PatentIndex {
    // Map from PAtent -> int (vertex index)
    private Map<Patent, Integer> patentToId;
    // Inverse mapping: index -> Patent
    private List<Patent> idToPatent;

    public PatentIndex() {
        patentToId = new HashMap<>();
        idToPatent = new ArrayList<>();
    }

    public int getOrCreateId(Patent patent) {
        Integer id = patentToId.get(patent);
        if (id == null) {
            id = patentToId.size();
            patentToId.put(patent, id);
            idToPatent.add(patent);
        }
        return id;
    }

    public int getId(Patent patent) {
        return patentToId.get(patent);
    }


    public Patent getPatent(int id) {
        return idToPatent.get(id);
    }

    public int size() {
        return patentToId.size();
    }
}
