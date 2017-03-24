/*
 * Minecraft Dev for IntelliJ
 *
 * https://minecraftdev.org
 *
 * Copyright (c) 2017 minecraft-dev
 *
 * MIT License
 */

package com.demonwav.mcdev.platform.forge;

import com.demonwav.mcdev.platform.BaseTemplate;
import com.demonwav.mcdev.util.MinecraftFileTemplateGroupFactory;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import java.util.Properties;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class ForgeTemplate {

    public static void applyBuildGradleTemplate(@NotNull Project project,
                                                @NotNull VirtualFile file,
                                                @NotNull VirtualFile prop,
                                                @NotNull String groupId,
                                                @NotNull String artifactId,
                                                @NotNull String forgeVersion,
                                                @NotNull String mcpVersion,
                                                @NotNull String pluginVersion,
                                                boolean spongeForge) {

        final Properties properties = new Properties();

        if (spongeForge) {
            properties.setProperty("SPONGE_FORGE", "true");
        }

        BaseTemplate.applyTemplate(project, file, MinecraftFileTemplateGroupFactory.FORGE_BUILD_GRADLE_TEMPLATE, properties);

        final Properties gradleProps = new Properties();
        gradleProps.setProperty("GROUP_ID", groupId);
        gradleProps.setProperty("ARTIFACT_ID", artifactId);
        gradleProps.setProperty("PLUGIN_VERSION", pluginVersion);
        gradleProps.setProperty("FORGE_VERSION", forgeVersion);
        gradleProps.setProperty("MCP_VERSION", mcpVersion);

        // create gradle.properties
        BaseTemplate.applyTemplate(project, prop, MinecraftFileTemplateGroupFactory.FORGE_GRADLE_PROPERTIES_TEMPLATE, gradleProps);
    }

    public static void applySubmoduleBuildGradleTemplate(@NotNull Project project,
                                                         @NotNull VirtualFile file,
                                                         @NotNull VirtualFile prop,
                                                         @NotNull String artifactId,
                                                         @NotNull String forgeVersion,
                                                         @NotNull String mcpVersion,
                                                         @NotNull String commonProjectName,
                                                         boolean spongeForge) {

        final Properties properties = new Properties();
        properties.setProperty("COMMON_PROJECT_NAME", commonProjectName);

        if (spongeForge) {
            properties.setProperty("SPONGE_FORGE", "true");
        }

        BaseTemplate.applyTemplate(project, file, MinecraftFileTemplateGroupFactory.FORGE_SUBMODULE_BUILD_GRADLE_TEMPLATE, properties);

        final Properties gradleProps = new Properties();
        gradleProps.setProperty("ARTIFACT_ID", artifactId);
        gradleProps.setProperty("FORGE_VERSION", forgeVersion);
        gradleProps.setProperty("MCP_VERSION", mcpVersion);

        // create gradle.properties
        BaseTemplate.applyTemplate(project, prop, MinecraftFileTemplateGroupFactory.FORGE_GRADLE_PROPERTIES_TEMPLATE, gradleProps);
    }

    public static void applyMcmodInfoTemplate(@NotNull Project project,
                                              @NotNull VirtualFile file,
                                              @NotNull String artifactId,
                                              @NotNull String pluginName,
                                              @NotNull String description,
                                              @NotNull String url,
                                              @NotNull String updateUrl,
                                              @Nullable String authorList,
                                              @Nullable String dependenciesList) {

        final Properties properties = new Properties();
        properties.setProperty("ARTIFACT_ID", artifactId);
        properties.setProperty("PLUGIN_NAME", pluginName);
        properties.setProperty("DESCRIPTION", description);
        properties.setProperty("URL", url);
        properties.setProperty("UPDATE_URL", updateUrl);

        if (authorList != null) {
            properties.setProperty("HAS_AUTHOR_LIST", "true");
            properties.setProperty("AUTHOR_LIST", authorList);
        }

        if (dependenciesList != null) {
            properties.setProperty("HAS_DEPENDENCIES_LIST", "true");
            properties.setProperty("DEPENDENCIES_LIST", dependenciesList);
        }

        BaseTemplate.applyTemplate(project, file, MinecraftFileTemplateGroupFactory.MCMOD_INFO_TEMPLATE, properties);
    }

    public static void applyMainClassTemplate(@NotNull Project project,
                                              @NotNull VirtualFile file,
                                              @NotNull String packageName,
                                              @NotNull String artifactId,
                                              @NotNull String pluginName,
                                              @NotNull String pluginVersion,
                                              @NotNull String className) {

        final Properties properties = new Properties();
        properties.setProperty("PACKAGE_NAME", packageName);
        properties.setProperty("ARTIFACT_ID", artifactId);
        properties.setProperty("PLUGIN_NAME", pluginName);
        properties.setProperty("PLUGIN_VERSION", pluginVersion);
        properties.setProperty("CLASS_NAME", className);

        BaseTemplate.applyTemplate(project, file, MinecraftFileTemplateGroupFactory.FORGE_MAIN_CLASS_TEMPLATE, properties);
    }
}
