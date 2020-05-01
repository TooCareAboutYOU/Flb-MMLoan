package com.mmkj.usercenter.model

import android.content.Context
import com.google.common.collect.HashMultimap
import com.google.common.collect.Multimap
import com.mmkj.usercenter.model.entity.RepayTypeEntity
import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * Author: wendjia
 * Time: 2018\9\8 0008
 * Description:
 */
object RepayChannelUtil {
    @JvmStatic
    lateinit var reginSet: HashSet<String>
    lateinit var reginoMap: Multimap<String, String>
    lateinit var provinceMap: Multimap<String, String>
    lateinit var cityMap: Multimap<String, RepayTypeEntity>
    lateinit var otherCityMap: Multimap<String, RepayTypeEntity>

    @JvmStatic
    fun create(context: Context) {
        reginSet = HashSet()
        reginoMap = HashMultimap.create()
        provinceMap = HashMultimap.create()
        cityMap = HashMultimap.create()
        otherCityMap = HashMultimap.create()
        parseFile("seven11.csv", context, true)
        parseFile("other.csv", context, false)
    }

    @JvmStatic
    fun parseFile(fileName: String, context: Context, seven11Mode: Boolean) {
        //获取assets资源管理器
        val assetManager = context.assets
        //通过管理器打开文件并读取
        BufferedReader(InputStreamReader(assetManager.open(fileName))).use {
            var line: String?
            while (it.readLine().also { line = it } != null) {
                parseCsv(line, seven11Mode)
            }
        }
    }

    @JvmStatic
    fun parseCsv(csvContent: String?, seven11Mode: Boolean) {
        var buffer: List<String>? = csvContent?.split(",")
        var reginoIndex = 8
        var provinceIndex = 7
        var cityIndex = 6
        if (buffer!!.size > 8) {
            var reginoStr = buffer.get(reginoIndex).trim().toUpperCase()
            var provinceStr = buffer.get(provinceIndex).trim().toUpperCase()
            var cityStr = buffer.get(cityIndex).trim().toLowerCase().toUpperCase()
            reginSet.add(reginoStr)
            reginoMap.put(reginoStr, provinceStr)
            provinceMap.put(reginoStr + provinceStr, cityStr)
            var repayTypeEntity = RepayTypeEntity()
            repayTypeEntity.platform = buffer.get(0).trim()
            repayTypeEntity.branchId = buffer.get(1).trim()
            repayTypeEntity.group = buffer.get(2).trim()
            repayTypeEntity.accountHolder = buffer.get(3).trim()
            repayTypeEntity.branchName = buffer.get(4).trim()
            repayTypeEntity.bracnchAddress = buffer.get(5).trim()
            repayTypeEntity.city = buffer.get(cityIndex).trim()
            repayTypeEntity.province = buffer.get(provinceIndex).trim()
            repayTypeEntity.regino = buffer.get(reginoIndex).trim()
            repayTypeEntity.businessType = buffer.get(reginoIndex + 1).trim()
            if (seven11Mode) {
                cityMap.put(reginoStr + provinceStr + cityStr, repayTypeEntity)
            } else {
                otherCityMap.put(reginoStr + provinceStr + cityStr, repayTypeEntity)
            }
        }
    }

    @JvmStatic
    fun getProvince(region: String): List<String> {
        return reginoMap.get(region).sorted().toList()
    }

    @JvmStatic
    fun getCity(region: String, province: String): List<String> {
        return provinceMap.get(region + province).sorted().toList()
    }

    @JvmStatic
    fun get711(region: String, province: String, city: String): List<RepayTypeEntity> {
        return cityMap.get(region + province + city).sortedByDescending { it.city }.toList()
    }

    @JvmStatic
    fun getOther(region: String, province: String, city: String): List<RepayTypeEntity> {
        return otherCityMap.get(region + province + city).sortedByDescending { it.city }.toList()
    }

    @JvmStatic
    fun clear() {
        reginSet.clear()
        reginoMap.clear()
        provinceMap.clear()
        cityMap.clear()
        otherCityMap.clear()
    }
}
