package is_palindrome;

/**
 * 给定一个字符串 s ，验证 s 是否是 回文串 ，只考虑字母和数字字符，可以忽略字母的大小写。
 * 本题中，将空字符串定义为有效的 回文串
 *
 * 什么是回文：
 * abba aba abccba是回文
 * aabd bdba 不是回文
 */
public class isPalindrome {
    /**
     * 思路：利用双指针实现，利用一头一尾两个指针逐个比较，有一个不同则返回false
     *
     * 写法：
     * 我们设置头尾双指针，然后分别向中间逼近
     * 当left或right指向的内容不在考虑范围内时跳过
     * 当left与right所指的字符不相等时立即终止
     * 如果循环结束仍未终止则返回True即可
     */

    public boolean isPalindrome(String s){
        int left = 0;
        int right = s.length() -1;
        while(left<=right){
            if(!Character.isLetterOrDigit(s.charAt(left))){
                left = left+1;
            }
            else if(!Character.isLetterOrDigit(s.charAt(right))){
                right = right-1;
            }
            else {
                char leftChar = Character.toLowerCase(s.charAt(left++));
                char rightChar = Character.toLowerCase(s.charAt(right--));
                if (leftChar != rightChar) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args){
        String ch1 = "abcba";
        String ch2 = "a";
        String ch3 = "asdfdfdsfds";
        String ch4 = "aba";
        String ch5 = "abccba";
        isPalindrome setmealTemplate = new isPalindrome();
        System.out.println(setmealTemplate.isPalindrome(ch1));
        System.out.println(setmealTemplate.isPalindrome(ch2));
        System.out.println(setmealTemplate.isPalindrome(ch3));
        System.out.println(setmealTemplate.isPalindrome(ch4));
        System.out.println(setmealTemplate.isPalindrome(ch5));
    }
}
