package cloud.isaura.adso.memory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.management.ManagementFactory;
import java.util.Optional;

public class MemoryUtils
{
    public static Long getCurrent() throws IOException
    {
        String name = ManagementFactory.getRuntimeMXBean().getName();
        String PID = name.substring(0, name.indexOf("@"));
        Process p = Runtime.getRuntime().exec("jcmd " + PID + " GC.class_histogram");
        try (BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
            Optional<String> total = input.lines().filter(s -> s.contains("Total")).findAny();
            if (total.isPresent()) {
                String[] split = total.get().split(" ");
                return Long.parseLong(split[split.length - 1]);
            }
        }
        return 0L;
    }

    public static Integer getNumberOfCores()
    {
        return Runtime.getRuntime().availableProcessors();
    }

    public static void print() throws IOException
    {
        String name = ManagementFactory.getRuntimeMXBean().getName();
        String PID = name.substring(0, name.indexOf("@"));
        Process p = Runtime.getRuntime().exec("jcmd " + PID + " GC.class_histogram");
        try (BufferedReader input = new BufferedReader(new InputStreamReader(p.getInputStream()))) {
             input.lines().forEach(System.out::println);
        }

    }
}
