function main1(c) {
    tasks
        .first(t => {
            return t.ready([0, 1, 2])
        })
        .thenLoop(10)
        // .firstLoopInfinite()
        .delayMillis(1000)
        .accumulator(0)
        .iterate(t => {
            // c.send(t.index)
            // c.send(t.accumulator)
            // t.iterationCount = 0
            // t.break()
            // t.nextDelayMillis = t.index * 1000
            // c.send(t.delay)
            return t.accumulate(t.accumulator + 1) // ?
        })
        .run(c)
}


function main(c) {
    tasks
        .first(t => {
            return t.ready([5, 10, 20])
        })
        .thenLoop(999)
        .delayMillis(1000)
        .accumulator(0)
        .iterate(t => {
            if (t.index == 15) {
                return t.ready(t.accumulator)
            }

            var arrayValue = t.result[t.index % 3];
            return t.accumulate(t.accumulator + arrayValue)
        })
        .run(c)
}

function caller() {
    test(c1, c2, state)
}

function test(c1, c2, state) {
    var a, b, c;
    if (state.idx == 0) {
        c1.send("hello")
    }
    else if (state.idx == 1) {
        c1.send("world")
        state.ended = true
    }
}

function test(c1, c2) {
    c1.send("hello")
    c2.send("world")
}
