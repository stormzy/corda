/*
 * R3 Proprietary and Confidential
 *
 * Copyright (c) 2018 R3 Limited.  All rights reserved.
 *
 * The intellectual and technical concepts contained herein are proprietary to R3 and its suppliers and are protected by trade secret law.
 *
 * Distribution of this file or any portion thereof via any medium without the express permission of R3 is strictly prohibited.
 */

package net.corda.node.internal

import net.corda.core.flows.FlowLogic
import net.corda.core.flows.InitiatedBy
import net.corda.core.internal.VisibleForTesting
import net.corda.core.internal.notary.NotaryService
import net.corda.core.messaging.CordaRPCOps
import net.corda.core.node.NodeInfo
import net.corda.node.services.api.StartedNodeServices
import net.corda.node.services.messaging.MessagingService
import net.corda.node.services.persistence.NodeAttachmentService
import net.corda.node.services.statemachine.StateMachineManager
import net.corda.nodeapi.internal.persistence.CordaPersistence
import rx.Observable

interface StartedNode {
    val services: StartedNodeServices
    val info: NodeInfo
    val smm: StateMachineManager
    val attachments: NodeAttachmentService
    val network: MessagingService
    val database: CordaPersistence
    val rpcOps: CordaRPCOps
    val notaryService: NotaryService?

    fun dispose()

    /**
     * Use this method to register your initiated flows in your tests. This is automatically done by the node when it
     * starts up for all [FlowLogic] classes it finds which are annotated with [InitiatedBy].
     * @return An [Observable] of the initiated flows started by counterparties.
     */
    fun <T : FlowLogic<*>> registerInitiatedFlow(initiatedFlowClass: Class<T>): Observable<T>
}