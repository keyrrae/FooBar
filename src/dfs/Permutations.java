package dfs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Christina on 1/13/16.
 */
public class Permutations {

    //O(n!), O(n)
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if(nums == null || nums.length == 0) return ans;
        helper(ans, new ArrayList<Integer>(), nums);
        return ans;
    }

    private void helper(List<List<Integer>> ans, List<Integer> path, int[]nums){
        if(path.size() == nums.length){
            ans.add(new ArrayList<Integer>(path));
        }

        for (int i = 0; i < nums.length;i++){
            if(path.contains(nums[i])) continue;
            path.add(nums[i]);
            helper(ans, path, nums);
            path.remove(path.size()-1);
        }
    }

    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
        boolean []visited = new boolean[nums.length];
        helper(ans, new ArrayList<Integer>(), nums, visited);
        return ans;
    }

    private void helper(List<List<Integer>> ans, List<Integer> path, int[] nums, boolean[] visited){
        if (path.size() == nums.length){
            ans.add(new ArrayList<Integer>(path));
            return;
        }

        for (int i = 0; i < nums.length; i++){
            if(visited[i]) continue;
            if(i>0 && nums[i] == nums[i-1] && !visited[i-1]){
                continue;
            }
            visited[i] = true;
            path.add(nums[i]);
            helper(ans, path, nums, visited);
            visited[i] = false;
            path.remove(path.size()-1);
        }
    }

    public static List<String> permutationsOfString(String s) {
        List<String> ans = new ArrayList<>();
        int len = s.length();
        char[] array = s.toCharArray();
        Arrays.sort(array);
        boolean[] visited = new boolean[len];
        dfs(ans, new StringBuilder(), array, visited, 0);
        return ans;
    }

    private static void dfs(List<String> ans, StringBuilder path, char[] array, boolean[] visited, int pos) {
        if (pos == array.length) {
            ans.add(path.toString());
            return;
        }
        for (int i = 0; i < array.length; i++) {
            if (visited[i]) {
                continue;
            }
            if (i != 0 && array[i] == array[i-1] && !visited[i-1]) {
                continue;
            }
            path.append(array[i]);
            visited[i] = true;
            dfs(ans, path, array, visited, pos+1);
            visited[i] = false;
            path.deleteCharAt(path.length()-1);
        }
    }

    public static void main(String[] arg) {
        Permutations a = new Permutations();
        System.out.println(a.permute(new int[]{1, 2, 3}));
        System.out.println(permutationsOfString("abc"));
    }
}
