package other;

/**
 * 32. 最长有效括号
 *
 * @see <a href="https://leetcode-cn.com/problems/longest-valid-parentheses/solution/zui-chang-you-xiao-gua-hao-by-leetcode/">Here</a>
 */
public class LongestValidParentheses {
    public static void main(String[] args) {
        System.out.println(longestValidParentheses("(()"));
    }

    /**
     * 从左向右遍历，可以找出“())”的情况，而“(()”这种从左到右是遍历不出来的，故还需从右向左遍历
     */
    public static int longestValidParentheses(String str){
        int left = 0;
        int right = 0;
        int max = 0;
        for(int i = 0; i < str.length(); i++){
            if(str.charAt(i) == '('){
                left++;
            }else{
                right++;
            }
            if(right == left){
                max = Math.max(max, left * 2);
            }else if(right > left){
                left = 0;
                right = 0;
            }
        }
        left = right = 0;
        for(int i = str.length() - 1; i >= 0; i--){
            if(str.charAt(i) == '('){
                left++;
            }else{
                right++;
            }
            if(right == left){
                max = Math.max(max, left * 2);
            }else if(left > right){
                left = 0;
                right = 0;
            }
        }
        return max;
    }
}