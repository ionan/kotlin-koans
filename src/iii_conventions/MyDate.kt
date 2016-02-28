package iii_conventions

import syntax.lambdas.closure
import java.util.*

data class MyDate(val year: Int, val month: Int, val dayOfMonth: Int) : Comparable<MyDate> {
    override operator fun compareTo(other: MyDate) : Int {
        if (year - other.year != 0) return year - other.year
        if (month - other.month != 0) return month - other.month
        return dayOfMonth - other.dayOfMonth
    }
}


operator fun MyDate.rangeTo(other: MyDate): DateRange = DateRange(this,other)

operator fun MyDate.plus(interval: TimeInterval): MyDate = this.addTimeIntervals(interval,1)

operator fun MyDate.plus(repInterval: RepeatedTimeInterval): MyDate =this.addTimeIntervals(repInterval.interval,repInterval.mul)

enum class TimeInterval {
    DAY,
    WEEK,
    YEAR
}

data class RepeatedTimeInterval(val mul : Int, val interval : TimeInterval)

operator fun TimeInterval.times(mul : Int) : RepeatedTimeInterval = RepeatedTimeInterval(mul,this)

class DateRange(override val start: MyDate, override val endInclusive: MyDate) : ClosedRange<MyDate>, Iterable<MyDate> {
    override fun iterator(): Iterator<MyDate>  = object  : Iterator<MyDate> {
        var current = start

        override fun next(): MyDate {
            if (!hasNext()) throw NoSuchElementException()
            val result = current
            current = current.nextDay()
            return result
        }

        override fun hasNext(): Boolean = current <= endInclusive

    }
}
