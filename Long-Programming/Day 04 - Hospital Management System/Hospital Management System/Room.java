import java.util.*;

class Room {
    private List<List<String>> roomList = new ArrayList<>();

    Room() {
        roomList.add(new ArrayList<>(Arrays.asList("1", "icu", "true", "50")));
        roomList.add(new ArrayList<>(Arrays.asList("2", "icu", "true", "50")));
        roomList.add(new ArrayList<>(Arrays.asList("3", "icu", "true", "50")));
        roomList.add(new ArrayList<>(Arrays.asList("4", "icu", "true", "50")));
        roomList.add(new ArrayList<>(Arrays.asList("5", "ent", "true", "15")));
        roomList.add(new ArrayList<>(Arrays.asList("6", "ent", "true", "15")));
        roomList.add(new ArrayList<>(Arrays.asList("7", "fever", "true", "10")));
        roomList.add(new ArrayList<>(Arrays.asList("8", "fever", "true", "10")));
        roomList.add(new ArrayList<>(Arrays.asList("9", "fever", "true", "10")));
        roomList.add(new ArrayList<>(Arrays.asList("10", "fracture", "true", "35")));
        roomList.add(new ArrayList<>(Arrays.asList("11", "fracture", "true", "35")));
        roomList.add(new ArrayList<>(Arrays.asList("12", "cancer", "true", "25")));
        roomList.add(new ArrayList<>(Arrays.asList("13", "cancer", "true", "25")));
    }

    public Long assignRoom(String statement) {
        for (List<String> list : roomList) {
            String type = list.get(1);
            boolean available = Boolean.parseBoolean(list.get(2));

            if (type.equalsIgnoreCase(statement) && available) {
                list.set(2, "false"); // occupy
                return Long.parseLong(list.get(0));
            }
        }
        return -1L;
    }

    public void releaseRoom(Long roomId) {
        for (List<String> list : roomList) {
            if (Long.parseLong(list.get(0)) == roomId) {
                list.set(2, "true"); // free
                return;
            }
        }
    }

    public double getRoomFee(Long roomId) {
        for (List<String> list : roomList) {
            if (Long.parseLong(list.get(0)) == roomId) {
                return Double.parseDouble(list.get(3));
            }
        }
        return 0;
    }
}