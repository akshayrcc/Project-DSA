package com.akshayram.s30.Greedy_5;

import com.akshayram.missing.Pair;

import java.util.*;

class BikesInCampus {
  List<List<Pair<Integer, Integer>>> workerToBikeList = new ArrayList<>();
  int closestBikeIndex[] = new int[1001];

  class WorkerBikePair {
    int workerIndex;
    int bikeIndex;
    int distance;

    WorkerBikePair(int workerIndex, int bikeIndex, int distance) {
      this.workerIndex = workerIndex;
      this.bikeIndex = bikeIndex;
      this.distance = distance;
    }
  }

  Comparator<WorkerBikePair> WorkerBikePairComparator =
      new Comparator<WorkerBikePair>() {
        @Override
        public int compare(WorkerBikePair a, WorkerBikePair b) {
          if (a.distance != b.distance) {
            return a.distance - b.distance;
          } else if (a.workerIndex != b.workerIndex) {
            return a.workerIndex - b.workerIndex;
          } else {
            return a.bikeIndex - b.bikeIndex;
          }
        }
      };

  int findDistance(int[] worker, int[] bike) {
    return Math.abs(worker[0] - bike[0]) + Math.abs(worker[1] - bike[1]);
  }

  void addClosestBikeToPq(PriorityQueue<WorkerBikePair> pq, int worker) {
    Pair<Integer, Integer> closestBike = workerToBikeList.get(worker).get(closestBikeIndex[worker]);
    closestBikeIndex[worker]++;

    WorkerBikePair workerBikePair =
        new WorkerBikePair(worker, closestBike.getValue(), closestBike.getKey());
    pq.add(workerBikePair);
  }

  public int[] assignBikes(int[][] workers, int[][] bikes) {
    PriorityQueue<WorkerBikePair> pq = new PriorityQueue<>(WorkerBikePairComparator);

    for (int worker = 0; worker < workers.length; worker++) {
      List<Pair<Integer, Integer>> bikeList = new ArrayList<>();
      for (int bike = 0; bike < bikes.length; bike++) {
        int distance = findDistance(workers[worker], bikes[bike]);
        bikeList.add(new Pair(distance, bike));
      }
      Collections.sort(bikeList, Comparator.comparing(Pair::getKey));

      workerToBikeList.add(bikeList);

      closestBikeIndex[worker] = 0;

      addClosestBikeToPq(pq, worker);
    }

    boolean bikeStatus[] = new boolean[bikes.length];

    int workerStatus[] = new int[workers.length];
    Arrays.fill(workerStatus, -1);

    while (!pq.isEmpty()) {
      WorkerBikePair workerBikePair = pq.remove();

      int worker = workerBikePair.workerIndex;
      int bike = workerBikePair.bikeIndex;

      if (workerStatus[worker] == -1 && !bikeStatus[bike]) {
        bikeStatus[bike] = true;
        workerStatus[worker] = bike;

      } else {
        addClosestBikeToPq(pq, worker);
      }
    }

    return workerStatus;
  }
}

class WildCardMatching {
  public boolean isMatch(String s, String p) {
    int sLen = s.length(), pLen = p.length();
    int sIdx = 0, pIdx = 0;
    int starIdx = -1, sTmpIdx = -1;

    while (sIdx < sLen) {

      if (pIdx < pLen && (p.charAt(pIdx) == '?' || p.charAt(pIdx) == s.charAt(sIdx))) {
        ++sIdx;
        ++pIdx;

      } else if (pIdx < pLen && p.charAt(pIdx) == '*') {

        starIdx = pIdx;
        sTmpIdx = sIdx;
        ++pIdx;

      } else if (starIdx == -1) {
        return false;

      } else {
        pIdx = starIdx + 1;
        sIdx = sTmpIdx + 1;
        sTmpIdx = sIdx;
      }
    }

    for (int i = pIdx; i < pLen; i++) {
      if (p.charAt(i) != '*') {
        return false;
      }
    }
    return true;
  }
}

