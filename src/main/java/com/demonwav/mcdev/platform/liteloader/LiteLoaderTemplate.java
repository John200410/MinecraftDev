/*
 * Minecraft Dev for IntelliJ
 *
 * https://minecraftdev.org
 *
 * Copyright (c) 2017 minecraft-dev
 *
 * MIT License
 */

package com.demonwav.mcdev.platform.liteloader;

import com.demonwav.mcdev.platform.BaseTemplate;
import com.demonwav.mcdev.util.MinecraftFileTemplateGroupFactory;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import java.util.Properties;
import org.jetbrains.annotations.NotNull;

public class LiteLoaderTemplate {

    public static void applyBuildGradleTemplate(@NotNull Project project,
                                                @NotNull VirtualFile file,
                                                @NotNull VirtualFile prop,
                                                @NotNull String groupId,
                                                @NotNull String artifactId,
                                                @NotNull String modVersion,
                                                @NotNull String mcVersion,
                                                @NotNull String mcpMappings) {

        final Properties properties = new Properties();
        properties.setProperty("GROUP_ID", groupId);
        properties.setProperty("ARTIFACT_ID", artifactId);
        properties.setProperty("VERSION", modVersion);
        properties.setProperty("MC_VERSION", mcVersion);
        properties.setProperty("MCP_MAPPINGS", mcpMappings);

        BaseTemplate.applyTemplate(project, prop, MinecraftFileTemplateGroupFactory.LITELOADER_GRADLE_PROPERTIES_TEMPLATE, properties);
        BaseTemplate.applyTemplate(project, file, MinecraftFileTemplateGroupFactory.LITELOADER_BUILD_GRADLE_TEMPLATE, new Properties());
    }

    public static void applySubmoduleBuildGradleTemplate(@NotNull Project project,
                                                         @NotNull VirtualFile file,
                                                         @NotNull VirtualFile prop,
                                                         @NotNull String modVersion,
                                                         @NotNull String mcVersion,
                                                         @NotNull String mcpMappings,
                                                         @NotNull String commonProjectName) {

        final Properties properties = new Properties();
        properties.setProperty("COMMON_PROJECT_NAME", commonProjectName);

            BaseTemplate.applyTemplate(project, file, MinecraftFileTemplateGroupFactory.LITELOADER_SUBMODULE_BUILD_GRADLE_TEMPLATE, properties);

        final Properties gradleProps = new Properties();
        gradleProps .setProperty("VERSION", modVersion);
        gradleProps .setProperty("MC_VERSION", mcVersion);
        gradleProps .setProperty("MCP_MAPPINGS", mcpMappings);

        BaseTemplate.applyTemplate(project, prop, MinecraftFileTemplateGroupFactory.LITELOADER_GRADLE_PROPERTIES_TEMPLATE, gradleProps);
    }

    public static void applyMainClassTemplate(@NotNull Project project,
                                              @NotNull VirtualFile file,
                                              @NotNull String packageName,
                                              @NotNull String className,
                                              @NotNull String modName,
                                              @NotNull String modVersion) {

        final Properties properties = new Properties();
        properties.setProperty("PACKAGE_NAME", packageName);
        properties.setProperty("CLASS_NAME", className);
        properties.setProperty("MOD_NAME", modName);
        properties.setProperty("MOD_VERSION", modVersion);

        BaseTemplate.applyTemplate(project, file, MinecraftFileTemplateGroupFactory.LITELOADER_MAIN_CLASS_TEMPLATE, properties);
    }
}
