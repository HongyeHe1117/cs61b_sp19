public class Palindrome {
    public Deque<Character> wordToDeque(String word){
        Deque<Character> word2 = new ArrayDeque<>();
        for(int i = 0; i < word.length(); i ++){
            word2.addLast(word.charAt(i));
        }
        return word2;
    }

    public boolean isPalindrome(String word){
        Deque D1 = wordToDeque(word);
        String Reword = new StringBuilder(word).reverse().toString();
        Deque D2 = wordToDeque(Reword);
        int wordlength = D1.size() / 2;
        if(word == null){
            return false;
        }else{
            if(D1.size() <= 1){
                return true;
            }else{
                return isPalindromehelper(wordlength, D1, D2);
            }
        }
    }

    private boolean isPalindromehelper(int wordlength, Deque D1, Deque D2){
        if(wordlength <= 0){
            return true;
        }else{
            if(D1.removeFirst() == D2.removeFirst()){
                return isPalindromehelper(wordlength - 1, D1, D2);
            }else{
                return false;
            }
        }
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque D1 = wordToDeque(word);
        String reword = new StringBuilder(word).reverse().toString();
        Deque D2 = wordToDeque(reword);
        int wordlength = D1.size() / 2;
        if (word == null) {
            return false;
        } else {
            if (D1.size() <= 1) {
                return true;
            } else {
                return isPalindromehelper(wordlength, cc, D1, D2);
            }
        }
    }
    private boolean isPalindromehelper(int wordlength, CharacterComparator cc, Deque D1, Deque D2){
        if(wordlength <= 0){
            return true;
        }else{
            if(cc.equalChars((char) D1.removeFirst(), (char) D2.removeFirst())){
                return isPalindromehelper(wordlength - 1, cc, D1, D2);
            }else{
                return false;
            }
        }
    }
}
