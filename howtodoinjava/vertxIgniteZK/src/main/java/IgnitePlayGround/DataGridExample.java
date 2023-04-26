package IgnitePlayGround;

import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.apache.ignite.cache.CacheAtomicityMode;
import org.apache.ignite.configuration.CacheConfiguration;
import org.apache.ignite.configuration.IgniteConfiguration;
import org.apache.ignite.spi.discovery.tcp.TcpDiscoverySpi;
import org.apache.ignite.spi.discovery.tcp.ipfinder.vm.TcpDiscoveryVmIpFinder;

import java.util.Arrays;
import java.util.List;

public class DataGridExample {
    public static void main(String[] args) {

        IgniteConfiguration cfg = new IgniteConfiguration();

        CacheConfiguration cacheCfg = new CacheConfiguration("CacheIsKing")
                .setAtomicityMode(CacheAtomicityMode.ATOMIC)
                .setBackups(1);

        TcpDiscoverySpi spi = new TcpDiscoverySpi();
        TcpDiscoveryVmIpFinder ipFinder = new TcpDiscoveryVmIpFinder();
        ipFinder.setAddresses(Arrays.asList("127.0.0.1", "127.0.0.1:45700..45709"));
        spi.setIpFinder(ipFinder);

        cfg.setCacheConfiguration(cacheCfg)
                .setDiscoverySpi(spi);

        try(Ignite ignite = Ignition.start()) {
            IgniteCache<Integer, String> cache = ignite.getOrCreateCache("CacheIsKing");

            for (int i = 0; i < 10; i++) {
                cache.put(i, Integer.toString(i));
            }

            List<String> configList = Arrays.asList(cfg.toString().split(","));
            configList.forEach(System.out::println);

            System.out.println("My Cache Is " +  cacheCfg.getName());

            for (int i = 0; i < 10; i++) {
                System.out.println("Got [key=" + i + ", val=" + cache.get(i) + "]");
            }


        }
    }
}
