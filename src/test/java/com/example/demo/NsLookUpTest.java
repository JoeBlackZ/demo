package com.example.demo;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Sets;
import org.assertj.core.util.TextFileWriter;
import org.junit.jupiter.api.Test;
import org.xbill.DNS.Lookup;
import org.xbill.DNS.Record;
import org.xbill.DNS.TextParseException;
import org.xbill.DNS.Type;

@Slf4j
public class NsLookUpTest {

    @Test
    public void lookUp() throws IOException {
        long start = System.currentTimeMillis();
        StringBuilder builder = new StringBuilder();
        Map<String, Set<String>> hostIPs = new HashMap<>(256);
        Set<String> hosts = Sets.newLinkedHashSet();
        int count = 1;
//		hosts.add("deskcloud-xa.hollysys.net");
        hosts.add("rhc.sonatype.com");
        // Docker Repositories
        hosts.add("hub.docker.com");
        hosts.add("index.docker.io");
        hosts.add("auth.docker.io");
        hosts.add("registry-1.docker.io");
        hosts.add("quay.io");
        hosts.add("mcr.microsoft.com");
        hosts.add("docker.mirrors.ustc.edu.cn");
        hosts.add("ustc-edu-cn.mirror.aliyuncs.com");
        hosts.add("production.cloudflare.docker.com");
        // Maven Repositories
        hosts.add("repo.maven.apache.org");
        hosts.add("repo1.maven.org");
        hosts.add("oss.sonatype.org");
        hosts.add("dl.google.com");
        hosts.add("maven.apache.org");
        hosts.add("maven.aliyun.com");
        hosts.add("developer.aliyun.com");
        hosts.add("maven.google.com");
        hosts.add("jcenter.bintray.com");
        hosts.add("repo.spring.io");
        hosts.add("plugins.gradle.org");
        hosts.add("repo.grails.org");
        hosts.add("repository.apache.org");
        hosts.add("jitpack.io");
        // NPM Repositories
        hosts.add("registry.npmjs.org");
        hosts.add("registry.npm.taobao.org");
        hosts.add("npm.taobao.org");
        hosts.add("registry.npmmirror.com");
        hosts.add("npmmirror.com");
        hosts.add("cdn.npmmirror.com");
        hosts.add("r.cnpmjs.org");
        //OS Repositories(Ubuntu/Centos/Alpine/Debian)
        hosts.add("mirrors.tuna.tsinghua.edu.cn");
        hosts.add("mirrors.aliyun.com");
        hosts.add("mirrors.cloud.tencent.com");
        hosts.add("archive.ubuntu.com");
        hosts.add("download.docker.com");
        hosts.add("releases.galeracluster.com");
        hosts.add("repos.influxdata.com");
        hosts.add("dl.influxdata.com");
        hosts.add("ppa.launchpad.net");
        hosts.add("launchpad.net");
        hosts.add("ppa.launchpadcontent.net");
        hosts.add("launchpadcontent.net");
        hosts.add("packages.redis.io");
        hosts.add("archive.kylinos.cn");
        hosts.add("update.cs2c.com.cn");
        hosts.add("nl.alpinelinux.org");
        //OS Repositories(Ubuntu/Debian)
        hosts.add("deb.nodesource.com");
        hosts.add("dl.yarnpkg.com");
        hosts.add("build.openmodelica.org");
        //OS Repositories(CentOS)
        hosts.add("rpm.nodesource.com");
        //Nuget Repositories
        hosts.add("api.nuget.org");
        hosts.add("www.nuget.org");
        hosts.add("azuresearch-usnc.nuget.org");
        hosts.add("azuresearch-ea.nuget.org");
        hosts.add("azuresearch-sea.nuget.org");
        //Raw Repositories
        hosts.add("cdnjs.cloudflare.com");
        hosts.add("github.com");
        //Ruby Repositories
        hosts.add("gems.ruby-china.com");
        hosts.add("rubygems.org");
        //Python Repositories
        hosts.add("pypi.tuna.tsinghua.edu.cn");
        hosts.add("pypi.org");
        //Bower Repositories
        hosts.add("registry.bower.io");
        //Go Repositories
        hosts.add("goproxy.io");
        hosts.add("proxy.golang.com.cn");
        hosts.add("golang.google.cn");
        // Helm Repositories
        hosts.add("charts.helm.sh");
        hosts.add("mirror.azure.cn");
        hosts.add("kubernetes-charts-incubator.storage.googleapis.com");
        // R Repositories
        hosts.add("cran.r-project.org");
        // Eclipse Repositories
        hosts.add("culmat.github.io");
        hosts.add("dl.ieclipse.cn");
        hosts.add("nodeclipse.github.io");
        hosts.add("jamling.github.io");
        hosts.add("download.eclipse.org");
        System.out.println("====域名解析开始(采样次数:" + count + ").....");
        Stream.iterate(1, i -> i + 1).limit(count).forEach(j -> {
            try {
                for (String host : hosts) {
                    Set<String> ips = hostToIps(host);
                    if (!hostIPs.containsKey(host)) {
                        hostIPs.put(host, Sets.newLinkedHashSet());
                    }
                    hostIPs.get(host).addAll(ips);
                    System.out.println(host);
                    System.out.println(String.join("\n", ips));
                    System.out.println();
                }
                System.out.println("====域名解析:已完成第" + j + "/" + count + "次采样");
                if (j < count) {
                    Thread.sleep(30 * 1000L);
                }
            } catch (Exception e) {
                log.error(e.getMessage());
            }
        });
        hostIPs.forEach((host, ips) -> {
            builder.append(host);
            ips.forEach(ip -> builder.append(",").append(ip).append("\n"));
        });
        String path = "./nexus-ips.csv";
        TextFileWriter.instance().write(new File(path), builder.toString());
        long end = System.currentTimeMillis();
        System.out.println("====域名解析最终结果[" + Duration.between(Instant.ofEpochMilli(start), Instant.ofEpochMilli(end)) + "]:");
        System.out.println(builder);
    }

    private Set<String> hostToIps(String host) throws TextParseException {
        Set<String> ips = Sets.newTreeSet();
        //查询域名对应的IP地址
        Lookup lookup = new Lookup(host, Type.A);
        Record[] answers = lookup.run();
        if (lookup.getResult() != Lookup.SUCCESSFUL) {
            System.out.println("[" + host + "] LOOKUP ERROR: " + lookup.getErrorString());
            return ips;
        }
        for (Record rec : answers) {
            String ip = rec.rdataToString();
            ips.add(ip);
        }
        return ips;
    }
}
