package com.example.vkr_new_disign.mathBlock

    fun expression(s: String, i:Int = 0): Numbers{
        var a = 0
        var ib = i
        var nm = multiply(s, ib)
        a = nm.a
        ib = nm.i
        while (ib < s.length && (s[ib] == '+' || s[ib] == '-')){
            var ch = s[ib]
            ib += 1
            var nmb = multiply(s, ib)
            var b = nmb.a
            ib = nmb.i
            if (ch == '+'){
                a += b
            }else{
                a -= b
            }
        }
        return Numbers(a, ib)
    }

    fun multiply(s: String, i: Int): Numbers{
        var a = 0
        var ib = i
        var nm = bracket(s, ib)
        a = nm.a
        ib = nm.i
        while ( ib < s.length && (s[ib] == '*' || s[ib] == '/')){
            var ch = s[ib]
            ib += 1
            var nmb = bracket(s, ib)
            var b = nmb.a
            ib = nmb.i
            if (ch == '*'){
                a *= b
            }else{
                a /= b
            }
        }
        return Numbers(a, ib)
    }

    fun bracket(s: String, i:Int): Numbers{
        var a = 0
        var ib = i
        var nm : Numbers
        if (s[ib] == '('){
            ib += 1
            nm = expression(s, ib)
            a = nm.a
            ib = nm.i + 1
        }else{
            nm = num(s, ib)
            a = nm.a
            ib = nm.i
        }
        return Numbers(a, ib)
    }

    fun num(s: String, i:Int): Numbers{
        var ib = i
        var st = ""
        val p = 1
        while (ib < s.length && s[ib] >= '0' && s[ib] <= '9'){
            st += s[ib]
            ib += 1
        }
        val stn = st.toInt()
        return Numbers(p * stn, ib)
    }

class Numbers(a: Int, i: Int){
    val a = a
    val i = i
}