####  "It is far easier to design a class to be thread-safe than to retrofit it for thread safety later."<br>
(Brian Goetz - Java concurrency: Publisher: Addison-Wesley Professional, Year: 2006; 2013)<br><br>
<img src="img/coroutine.png" height="320" />
<br>
### Coroutine<br>
A coroutine is Kotlin's way of tackling asynchronous operations, multi-threaded programming<br>
or manging concurrency.<br>
According to Coroutine Team, Java threads have below problems:
- Context switching for thread can be a costly operation;
- Various the various OS does restrict the number of threads created by per app. They can<br>
  be infinite;
- There are still a few platforms that do not support threads (JavaScript);
- It's always difficult to debug threads;
- Dealing with race conditions can be a nightmare.
#### => Coroutine has answered all the above problems & it also runs in parallel.

### References
- ##### https://github.com/Kotlin/kotlinx.coroutines
- Anko-Coroutines: https://github.com/Kotlin/anko/wiki/Anko-Coroutines