package com.example.vkr_new_disign.mathBlock

    fun expression(s: String, i:Int = 0): Numbers{
        println("=> e s= $s i =$i")

        var a = 0
        var ib = i

        println("=> e => m ")

        var nm = multiply(s, ib)

        a = nm.a
        ib = nm.i

        println("=> e => m => m $a $ib")

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

        println("A = $a")
        return Numbers(a, ib)
    }

    fun multiply(s: String, i: Int): Numbers{
        println("=> m $s $i")

        var a = 0
        var ib = i

        println("=> m => b ")

        var nm = bracket(s, ib)

        a = nm.a
        ib = nm.i

        println("=> m => b => m $a $ib")

        while ( ib < s.length && (s[ib] == '*' || s[ib] == '/')){

            var ch = s[ib]
            ib += 1

            println("=> m => b $s $i")

            var nmb = bracket(s, ib)

            var b = nmb.a

            ib = nmb.i

            println("=> m => b => m $b $ib")

            if (ch == '*'){
                a *= b
            }else{
                a /= b
            }

        }

        return Numbers(a, ib)
    }

    fun bracket(s: String, i:Int): Numbers{
        println("=> b $s $i")
        var a = 0
        var ib = i
        var nm : Numbers

        if (s[ib] == '('){

            ib += 1

            println("=> b => e $a $ib")

            nm = expression(s, ib)
            a = nm.a

            ib = nm.i + 1

            println("=> b => e => b$a $ib")

        }else{
            println("=> b => n $a $ib")
            nm = num(s, ib)

            a = nm.a
            ib = nm.i
            println("=> b => n => b $a $ib")

        }


        return Numbers(a, ib)
    }

    fun num(s: String, i:Int): Numbers{

        println("=> n $s $i")

        var ib = i

        var st = ""
        val p = 1

        while (ib < s.length && s[ib] >= '0' && s[ib] <= '9'){

            st += s[ib]
            ib += 1
        }

        println("=> n - st $st")

        val stn = st.toInt()

        println("=> n - stn $stn")

        return Numbers(p * stn, ib)

    }

class Numbers(a: Int, i: Int){
    val a = a
    val i = i


}