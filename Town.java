//Time = O(T + N), where T is the number of trust relationships and N is the number of people in the town
//Space = O(n), as we are using n+1 array size

class Solution {
    public int findJudge(int n, int[][] trust) {
        // Initialize an array to keep track of the number of trusts each person has
        int[] trustCounts = new int[n + 1];
        
        // Iterate over the trust relationships and update the trust counts
        for (int[] relationship : trust) {
            int trustor = relationship[0];
            int trustee = relationship[1];
            trustCounts[trustor]--;
            trustCounts[trustee]++;
        }
        
        // Iterate over the trust counts and find the person with n-1 trusts
        for (int i = 1; i <= n; i++) {
            if (trustCounts[i] == n - 1) {
                // Check that the person doesn't trust anyone else
                for (int j = 1; j <= n; j++) {
                    if (i != j && trustCounts[j] >= 0) {
                        return -1;
                    }
                }
                return i;
            }
        }
        
        // If there is no such person, return -1
        return -1;
    }
}
