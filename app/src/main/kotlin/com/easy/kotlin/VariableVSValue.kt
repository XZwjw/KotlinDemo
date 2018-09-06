package com.easy.kotlin

import android.util.Log
import java.lang.Integer.parseInt
import java.lang.reflect.Field
import kotlin.properties.Delegates

/**
 * Description:Kotlin的基本语法操作
 * Created by wangjiawang on 2018/9/4.
 * complete
 */
class VariableVSValue {
    private val TAG = "VariableVSValue"
    /**
     * val 与var 的使用
     * val 不可变属性
     * var 可变属性
     */
    fun declareVar() {
        var a = 1
        a = 2
        Log.d(TAG,""+a)
        Log.d(TAG,""+a::class)
        Log.d(TAG,""+a::class.java)
        var x = 5
        x += 1
        Log.d(TAG,"x = $x")
    }

    /**
     * :的使用
     */
    fun declareVal() {
        val b = "a"
//        b  = "b" //编译器会报错： Val cannot be reassigned
        Log.d(TAG,b)
        Log.d(TAG,""+b::class)
        Log.d(TAG,""+b::class.java)

        val c: Int = 1  // 立即赋值
        val d = 2   // 自动推断出 `Int` 类型
        val e: Int  // 如果没有初始值类型不能省略
        e = 3       // 明确赋值
        Log.d(TAG,"c = $c, d = $d, e = $e")


    }

    /**
     * String,1,Object
     *
     */
    fun getLength(obj: Any) : Int?{
        var result = 0
        if(obj is String) {
            Log.d(TAG,""+obj::class)
            result = obj.length
            Log.d(TAG,""+result)
        }
        Log.d(TAG,""+obj::class)
        return result
    }

    /**
     * 字符串的使用
     * 使用"""xxxxxxxxxx""",三引号代替双引号
     */
    fun stringTest() {
        val rawString = """
        fun hellWorld(val name :String) {
            println("Hello,world")
        }

    """
        Log.d(TAG,rawString)
    }

    /**
     * 常见例子,比大小
     * if {     //if可带返回值，相当于方法
     *  ...
     *  最后一句（相当于返回值）
     * }else    //else可带返回值，相当于方法
     */
    fun max(a :Int,b:Int) : Int {
        val max = if (a >b) a else b
        return max  //return if (a > b) a else b
    }

    /**
     * 同上
     */
    fun max1(a : Int,b:Int):Int {
        //传统用法
        var max1 = a
        if(a < b) max1 = b
        return max1
    }

    /**
     * When的使用：
     */
    fun whenTest(x:Int) {
        val s = "123"
        when(x) {
            -1,0 -> Log.d(TAG,"x == -1 or x ==0")
            1 -> Log.d(TAG,"x==1")
            2 -> Log.d(TAG,"x ==2")
            8 -> Log.d(TAG,"x is 8")
            parseInt(s) -> Log.d(TAG,"x is 123")
            else -> {
                Log.d(TAG,"x is neither 1 nor 2")
            }
        }
    }

    /**
     * in 在Test中的使用
     */
    fun inTest() {
        val  x = 1
        val validNumbers = arrayOf(1,2,3)
        when (x) {
            in 1..10 -> print("x is in the range")
            in validNumbers -> print("x is valid")
            !in 10..20 -> print("x is outside the range")
            else -> print("none of the above")
        }
    }

    /**
     * in在for中的使用
     */
    fun forTest(args: Array<String>) {
        for (arg in args) {
            Log.d(TAG,arg)
        }
        //or
        println()
    }

    /**
     * kotlin声明类型,基本类型声明方式: xxxArray
     * 非基本类型数组：Array<xxx>
     */
    fun NumberTest() {
        Log.d(TAG, println().toString())
        val iArray: IntArray = intArrayOf(1,2,3)
        val sArray: Array<String> = arrayOf("阿尔萨斯","巫妖王","恐惧魔王")
        val s1Array: Array<String> = Array<String>(3,{i->i.toString()})
        val anyArray: Array<Any> = arrayOf(1,'2',3.0,4.1f)  //可将类型进行混排放入同一个数组中
        val lArray: LongArray = longArrayOf(1L,2L,3L)
    }

    class Function {
        private val TAG ="Function"
        /**
         * 求和
         */
//        fun sum(a: Int,b: Int): Int{
//            return a+b
//        }

        //求和简化
        fun sum(a: Int,b: Int) = a+b

        //取最大值
        fun max1(a: Int,b: Int) = if(a > b)a else b

        //取最大值，if后多个表达式
        fun max2(a: Int,b: Int) = if (a > b) {
            Log.d(TAG,a.toString())
            a
        }else {
            Log.d(TAG,b.toString())
            b
        }

        //无返回值
        fun printInt(a: Int): Unit {
            println(a)
        }

        //无返回值，省略Unit
        fun printInt2(a:Int) {
            println(a)
        }

        //Kotlin类默认为final，不可继承，

        fun Test(){}
    }

    /**
     * 操作符
     */
    class Operator {
        var a: Int = 4
        //左移运算符
        var shl: Int = a shl (1)
        //右移运算符
        var shr: Int = a shr (1)
        //无符号右移
        var ushr: Int = a ushr (3)
        //按位与操作
        var and: Int = 2 and (4)
        //按位或操作
        var or: Int = 2 or (4)
        //按位异或操作
        var xor: Int = 2 xor (6)
    }

    class ArrayLoop{
        fun forLoop(array:Array<String>) {
            //直接输出字符
            for(str in array) {
                println(str)
            }
            //Array提供了forEach函数
            array.forEach {
                println(it)
            }
            //array.indices是数组索引
            for (i in array.indices) {
                println(array[i])
            }
            //类似java(int i=0;...)
            var i = 0
            while (i < array.size) {
                println(array[i++])
            }
            //使用when判断类型
            fun whenFun(obj: Any) {
                when(obj) {
                    25 -> println("25")
                    "kotlin" -> println("kotlin")
                    !is String -> println("Not String")
                    is Long -> println("Number is Long")
                    else -> println("Nothing")
                }
            }

            //@定义label
            loop@ for (i in 0..2) {
                for (j in 0..3) {
                    println("i:"+i+"j:"+j)
                    if(j == 2)
                        break@loop
                }
            }
            //倒序输出
            for (i in 5 downTo 0){
                println(i)  //输出543210
            }

            //设置输出数据步长
            for(i in 1..5 step 3) {
                println(i)  //输出14
            }
            //step和downTo混用
            for (i in 5 downTo 0 step 3) {
                println(i)  //输出52
            }



        }


    }

    /**
     * 类与枚举
     */
    class ClassAndEnum {

        open class People private constructor(var id: String,var name: String){
            //可在类中初始化属性：
            var customName = name.toUpperCase()//初始化属性

            constructor(id: String,name: String,age: Int): this(id, name) {
                println("constructor")
            }

            init {
                println("初始化操作，可使用constructor参数")
            }

            open fun study(){
                print("study")
            }

            class Student(id: String,name: String):People(id, name) {
                var test:Number = 3
                private var name1:String?
                    get() {
                        return name1
                    }
                    set(value) {
                        name1 = value
                    }

                override fun study() {
                    super.study()

                }
            }
        }

    }

    data class Staff<T>(var name:String,val position:String,var age:T)


    fun staffTest(){
        var staff = Staff("code4Android","Android工程师","22") //实例化对象
//        staff.position = "前端"         //val不能再次赋值
        staff.name = "code4Android2"
        var  staff1 = staff.copy()
        //使用copy的时候可以指定默认值，可以指定任意个用逗号","隔开
        var staff2 = staff.copy(name = "ccc",position = "kotlin")
        println("name:${staff2.name} position:${staff2.position} age ${staff2.age}")
        //staff.position ="kotlin"//val不能再次被赋值
        var anotherStaff = Staff("code4Android","Android工程师",22)//实例化对象
        println("staff to String():${staff.toString()} anotherStaff to String():${anotherStaff.toString()}" )
        println("staff hasCode():${staff.hashCode()} anotherStaff hasCode():${anotherStaff.hashCode()}" )
        println("staff is equals another staff ? ${staff.equals(anotherStaff)}" )
    }

    /**
     * 匿名内部类
     */
    open class KeyBord{
        val TAG = "KeyBord"
        open fun onKeyEvent(code: Int): Unit = Unit
        fun print() {
            println(TAG+onKeyEvent(5))
        }

        var key = object :KeyBord(){
            override fun onKeyEvent(code: Int): Unit = Unit
        }
    }

    enum class Color {
        RED,BLACK,BLUE,GREEN,WHITE
    }
    fun display() {
        var color:Color = Color.BLACK
        Color.valueOf("BLACK")//转换指定name为枚举值，若未匹配成功，会抛出IllegalArgumentException
        Color.values()//以数组的形式，返回枚举值
        println(color.name) //获取枚举名称;输出BLACK
        println(color.ordinal)  //获取枚举值在所有枚举数组中定义的顺序，0开始;输出1

    }

    class Employee(var name: String) {
        fun print() {
            println("Employee")
        }
    }

    //扩展函数
    fun Employee.println(){
        print("println:Employee name is $name")
    }

    /**
     * 扩展一个空函数
     */
    fun Any?.toString1():String {
        return if(this == null)
            "为空啊"
        else
            "toString:"+toString()
    }

    /**
     * 扩展属性,为val类型，不能使用set方法
     */
    val Employee.lastName : String
        get() {
            return "get"+name
        }

    /**
     * 被代理接口
     */

    interface Base {
        fun display()
    }

    /**
     * 被代理的类
     */
    open class BaseImpl : Base {
        override fun display() = print("just display me.")

    }

    class Drived(base:Base): Base by base

    fun show(){
        var b = BaseImpl()
        var drived = Drived(b)
        drived.display()
    }

    /**
     * 代理类型
     * 懒加载: Lazy
     * 观察者:Delegates.observable()
     * 非空属性:Delegates.notNull<>()
     */

    class Water{
        public var weight: Int by Delegates.notNull()
        /**
         * 代理属性
         */
        public val name: String by lazy {
            println("lazy")
            "Code4Android"
        }

        public var value: String by Delegates.observable("init value") {
            d,old,new->
            println("$d-->$old-->$new")
        }
    }

    fun delegateTest(){
        show()
        val water = Water()
        println(water.name)
        println(water.name)
        water.value ="11111"
        water.value ="22222"
        water.value ="33333"
        println(water.value)
        println(water.value)
        water.weight = 2
        println(water.weight)
    }

    /**
     * Operator
     */
    val String.lastChar: Char
        get() = this[this.length - 1]
    class A(val p: Int)

    //1,反射的到运行时类引用
    val c = ClassAndEnum.People.Student::class
    //2,函数引用
    fun isOdd(x: Int) = x % 2!= 0

    val numbers = listOf(1,2,3,4)

    fun operatorTest(){
        //2,函数引用
        println("numbers.filter(::isOdd): "+numbers.filter(::isOdd))
        //3,属性引用
        println("::counter.get(): "+::counter.get())
        ::counter.set(2)    //在set中设置feild = value后这里的set才能起作用

        println("counter: "+counter)
        //4,::x表达式评估为KProperty类型的属性,它允许我们使用get()读它的值或者使用名字取回它的属性
        val prop = A::p
        println("prop.get(A(1)): "+prop.get(A(1)))
        //5,对于扩展属性,该功能已过时
//        println(String::lastChar.get("abc"))
        //6,与java反射调用
        println("A::p.javaClass: "+A::p.javaClass)
        var f: Array<Field> = A::p.javaClass.declaredFields
        println("f: "+f)
    }


    open class People constructor(var id: String,var name: String) {
        //可以在类中初始化属性
        var customName = name.toUpperCase() //初始化属性
        //使用constructor前缀声明，且必须调用primary constructor,使用this关键字
        constructor(id: String,name: String,age:Int): this(id,name) {
            println("constructor")
        }
        init {
            println("初始化操作，可使用constructor参数")
            println("People.ID: "+People.ID)
            println(customName)
        }

        companion object {
            val ID = 1
        }

    }

    /**
     * 使用object定义类，该类的实例即为单例，访问单例直接使用类名，不能通过构造函数进行访问，不允许有构造函数
     */
    object Singleton {
        fun doSomething(){
            println("doSomething")
        }
    }

    /**
     * 实例化的时候，单例是懒加载，当使用的时候才去加载；而对象表达式在初始化的地方去加载。
     *
     * 当在类内部使用object关键字定义对象时，允许直接通过外部类的类名访问内部对象进而访问其相关属性和方法，相当于静态变量
     *
     * 可以使用companion修饰单例，则访问属性或方法时，允许省略单例名
     * 如:在该类中,VariableVSValue.MyClass.doSomething() 省略对象Singleton的调用
     *  MyClass.doSomething()   //访问内部单例对象方法
     */
    class MyClass {
        companion object Singleton{      //companion静态
            fun doSomething() {
                println("doSomething")
            }
        }
    }





    //3.属性引用

    var pp = 1
        set(value) {
            if(value >= 0)
                field = value
        }
    var counter = 0 //初始化给定的值将直接写入后端域变量中
        set(value) {
            if (value >= 0)
                field = value
        }



    fun Test(){

    }






}